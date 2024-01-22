package com.ronit.resoluteaiassignment.feature_auth.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ronit.resoluteaiassignment.feature_auth.data.repository.LoginResult
import com.ronit.resoluteaiassignment.feature_auth.domain.repository.LoginRepository
import com.ronit.resoluteaiassignment.feature_auth.ui.login.event.LoginScreenEvent
import com.ronit.resoluteaiassignment.feature_auth.ui.login.state.LoginScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
        private val loginRepository: LoginRepository
):ViewModel() {

        var loginScreenState by mutableStateOf(LoginScreenState())
                private set


        fun onLoginScreenEvent(
                event:LoginScreenEvent
        ){
                when(event){
                        is LoginScreenEvent.EmailEntered -> {
                                loginScreenState=loginScreenState.copy(email = event.email)
                        }
                        is LoginScreenEvent.PasswordEntered -> {

                                loginScreenState=loginScreenState.copy(password = event.password)
                        }

                        is LoginScreenEvent.LoginEvent -> {

                                loginRepository.login(
                                        email = loginScreenState.email,
                                        password = loginScreenState.password
                                ){loginResult:LoginResult ->

                                      when (loginResult){
                                              is LoginResult.Failure -> {

                                              }
                                              is LoginResult.Success -> {
                                                      event.onSuccess()
                                              }
                                      }
                                }
                        }

                        is LoginScreenEvent.SignUpEvent -> {
                                event.onSignUpBtnClicked()
                        }
                }
        }




}