package com.ronit.resoluteaiassignment.feature_auth.data.repository

import android.app.Activity
import android.util.Log
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.ronit.resoluteaiassignment.feature_auth.domain.model.UserInfo
import com.ronit.resoluteaiassignment.feature_auth.domain.repository.SignUpRepository
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
        val auth:FirebaseAuth
):SignUpRepository {


    override fun SignUp(userInfo: UserInfo,activity: Activity,onCodeSent:(String)->Unit) {

        val callbacks = object :PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                Log.d("otp",p0.smsCode.toString())
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Log.w("otp", "onVerificationFailed", e)

                if (e is FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                } else if (e is FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                } else if (e is FirebaseAuthMissingActivityForRecaptchaException) {
                    // reCAPTCHA verification attempted with null Activity
                }

            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(p0, p1)
                onCodeSent(p0)
            }

        }
        val options= PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(userInfo.phoneNumber)
            .setTimeout(60L,TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(callbacks)
            .build()

       PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun signInWithEmail(email:String,password:String){

        auth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {result:AuthResult->

            }
    }
}