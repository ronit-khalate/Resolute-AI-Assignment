package com.ronit.resoluteaiassignment.feature_auth.data.repository

import com.google.firebase.auth.FirebaseUser

sealed class LoginResult {

    data class Success(val user:FirebaseUser):LoginResult()

    data class Failure(val error:Exception):LoginResult()
}