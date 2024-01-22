package com.ronit.resoluteaiassignment.feature_auth.ui.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.autoSaver
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import com.ronit.resoluteaiassignment.feature_auth.domain.model.UserInfo
import com.ronit.resoluteaiassignment.feature_auth.domain.repository.SignUpRepository
import com.ronit.resoluteaiassignment.feature_auth.ui.signup.event.SignUpEvent
import com.ronit.resoluteaiassignment.feature_auth.ui.signup.event.VerificationEvent
import com.ronit.resoluteaiassignment.feature_auth.ui.signup.state.SignUpState
import com.ronit.resoluteaiassignment.feature_auth.ui.signup.state.VerificationState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
        private val signUpRepository: SignUpRepository
):ViewModel(){


    var signUpState by mutableStateOf(SignUpState())
        private set

    var verificationState by mutableStateOf(VerificationState())
        private set

    var receivesCode:String=""
        private set



    fun onSignUpEventChanged(event:SignUpEvent){

        when(event){
            is SignUpEvent.EmailEntered -> {

                signUpState=signUpState.copy(email = event.email)
            }
            is SignUpEvent.MobileNumberEntered -> {

                signUpState=signUpState.copy(mobileNumber = event.number)
            }
            is SignUpEvent.PasswordEntered -> {

                signUpState=signUpState.copy(password = event.password)
            }
            is SignUpEvent.SignUp -> {
                signUpRepository.SignUp(UserInfo(
                        email = signUpState.email,
                        phoneNumber = "+91${signUpState.mobileNumber}",
                        password = signUpState.password
                ),event.activity,event.onCodeSent)
            }

            is SignUpEvent.LoginEvent->{
                event.onLoginBtnClicked()
            }
        }
    }

    fun onVerificationEventChanged(event: VerificationEvent,onChangeFocus:()->Unit){

        val digit:String = if(event.digit.length>1){
            event.digit.last().toString()
        } else
            event.digit

        when(event){


            is VerificationEvent.FirstDigitEntered -> {
                verificationState=verificationState.copy(firstDigit = digit)
                onChangeFocus()

            }
            is VerificationEvent.SecondDigitEntered ->{
                verificationState=verificationState.copy(secondDigit = digit)
                onChangeFocus()

            }
            is VerificationEvent.ThirdDigitEntered ->{
                verificationState=verificationState.copy(thirdDigit = digit)
                onChangeFocus()
            }
            is VerificationEvent.FourthDigitEntered -> {
                verificationState=verificationState.copy(fourthDigit = digit)
                onChangeFocus()
            }

            is VerificationEvent.FifthDigitEntered -> {
                verificationState=verificationState.copy(fifthDigit = digit)
                onChangeFocus()
            }
            is VerificationEvent.SixthDigitEntered -> {
                verificationState=verificationState.copy(sixthDigit = digit)
                onChangeFocus()
            }

           is VerificationEvent.Verify->{

               val credential= PhoneAuthProvider.getCredential(receivesCode,verificationState.getTypedOtp())
               Firebase.auth.createUserWithEmailAndPassword(signUpState.email,signUpState.password)
                   .addOnSuccessListener {
                       it.user!!.linkWithCredential(credential).addOnSuccessListener {
                           event.signUp()
                       }
                   }
           }
        }
    }

    fun setReceivedOtp(otp:String){
        this.receivesCode=otp
    }
}