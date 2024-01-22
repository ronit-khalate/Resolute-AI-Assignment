package com.ronit.resoluteaiassignment.core.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ronit.resoluteaiassignment.feature_auth.data.repository.LoginRepositoryImpl
import com.ronit.resoluteaiassignment.feature_auth.data.repository.SignUpRepositoryImpl
import com.ronit.resoluteaiassignment.feature_auth.domain.repository.LoginRepository
import com.ronit.resoluteaiassignment.feature_auth.domain.repository.SignUpRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun provideLoginRepository():LoginRepository{

        return LoginRepositoryImpl(Firebase.auth)
    }

    @Provides
    fun provideSignUpRepository():SignUpRepository{
        return SignUpRepositoryImpl(Firebase.auth)
    }
}