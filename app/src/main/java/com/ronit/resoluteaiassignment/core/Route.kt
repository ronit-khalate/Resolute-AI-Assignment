package com.ronit.resoluteaiassignment.core

sealed class Route(val route:String) {

    object Login:Route("loginScreen")
    object SignUp:Route("signUpRoute")
    object Profile:Route("profileRoute")
    object Verification:Route("verificationRoute")
}