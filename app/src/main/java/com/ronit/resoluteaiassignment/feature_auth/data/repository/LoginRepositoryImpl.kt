package com.ronit.resoluteaiassignment.feature_auth.data.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.ronit.resoluteaiassignment.feature_auth.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val auth:FirebaseAuth,

): LoginRepository {

    override fun login(
        email:String,
        password:String,
        onResult:(LoginResult)->Unit
    ){

        auth.signInWithEmailAndPassword(email.trim(),password.trim())
            .addOnSuccessListener {authResult:AuthResult->

               onResult(LoginResult.Success(authResult.user!!))
            }
            .addOnFailureListener {exception:Exception->
                onResult(LoginResult.Failure(exception))
            }
    }
}