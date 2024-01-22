package com.ronit.resoluteaiassignment.feature_auth.ui.signup.event

import android.app.Activity

sealed class SignUpEvent {

    data class PasswordEntered(val password:String):SignUpEvent()
    data class EmailEntered(val email:String):SignUpEvent()
    data class MobileNumberEntered(val number:String):SignUpEvent()
    data class LoginEvent(val onLoginBtnClicked:()->Unit):SignUpEvent()
    data class SignUp(val activity:Activity,val onCodeSent:(String)->Unit):SignUpEvent()
}