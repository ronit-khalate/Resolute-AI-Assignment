package com.ronit.resoluteaiassignment.feature_auth.domain.repository

import android.app.Activity
import com.ronit.resoluteaiassignment.feature_auth.domain.model.UserInfo

interface SignUpRepository {

    fun SignUp(userInfo:UserInfo,activity: Activity,onCodeSent:(String)->Unit)
}