package com.ronit.resoluteaiassignment.feature_auth.domain.repository

import com.ronit.resoluteaiassignment.feature_auth.data.repository.LoginResult

interface LoginRepository {

    fun login(email:String, password:String,onResult:(LoginResult)->Unit)
}