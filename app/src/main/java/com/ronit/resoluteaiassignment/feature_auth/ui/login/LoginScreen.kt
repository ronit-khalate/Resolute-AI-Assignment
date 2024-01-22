package com.ronit.resoluteaiassignment.feature_auth.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ronit.resoluteaiassignment.R
import com.ronit.resoluteaiassignment.core.Route
import com.ronit.resoluteaiassignment.feature_auth.ui.login.event.LoginScreenEvent


@Composable
fun LoginScreen(
    navController: NavController
){

    // Show password state
    val viewModel:LoginViewModel = hiltViewModel()
    var showPassword by remember { mutableStateOf(false) }
    Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Row(
                modifier = Modifier
                    .padding(start = 30.dp, end = 30.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
        ){

            Column {
                Text(
                        text = "Welcome",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = "Sign In To Continue")
            }

            Spacer(modifier = Modifier.width(115.dp))
            Text(
                    modifier = Modifier
                        .padding( bottom = 27.dp)
                        .clickable {
                            viewModel.onLoginScreenEvent(LoginScreenEvent.SignUpEvent{
                                navController.navigate(Route.SignUp.route)
                            })
                        },
                    text = "Sign Up",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
            )

        }


        Spacer(modifier = Modifier.height(30.dp))
        TextField(
                modifier = Modifier
                    .padding(start = 30.dp, end = 30.dp)
                    .fillMaxWidth(),
                value =viewModel.loginScreenState.email ,
                onValueChange = {email:String->
                        viewModel.onLoginScreenEvent(
                                LoginScreenEvent.EmailEntered(email)
                        )
                },
                label = { Text(text = "Enter Email")},
                placeholder = { Text(text = "Email")}
        )

        Spacer(
                modifier = Modifier.height(20.dp)
        )

        TextField(
                modifier = Modifier
                    .padding(start = 30.dp, end = 30.dp)
                    .fillMaxWidth(),
                value = viewModel.loginScreenState.password ,
                visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                onValueChange = {password:String->

                        viewModel.onLoginScreenEvent(
                                LoginScreenEvent.PasswordEntered(password)
                        )
                },
                label = { Text(text = "Enter Password")},
                placeholder = { Text(text = "Email")},
                trailingIcon = {


                        Image(
                                modifier = Modifier
                                    .clickable {
                                       showPassword=!showPassword
                                    },
                                painter = if(showPassword) {
                                    painterResource(id = R.drawable.visibility_off)
                                }else{
                                    painterResource(id = R.drawable.visibility)
                                },
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)

                        )

                }

        )

        Spacer(
                modifier = Modifier.height(40.dp)
        )

        OutlinedButton(
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp),
                onClick = {
                    viewModel.onLoginScreenEvent(LoginScreenEvent.LoginEvent{
                        navController.navigate(Route.Profile.route)
                    })
                }
        ) {

            Text(
                    text = "Log In",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
            )
        }

    }

}