package com.ronit.resoluteaiassignment.feature_auth.ui.login.event

import androidx.compose.ui.text.input.VisualTransformation

sealed class LoginScreenEvent {

    data class EmailEntered(val email:String):LoginScreenEvent()
    data class PasswordEntered(val password:String):LoginScreenEvent()
    data class LoginEvent(val onSuccess:()->Unit):LoginScreenEvent()
    data class SignUpEvent(val onSignUpBtnClicked:()->Unit):LoginScreenEvent()
}