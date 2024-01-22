package com.ronit.resoluteaiassignment.feature_auth.ui.login

import android.app.Activity
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
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ronit.resoluteaiassignment.R
import com.ronit.resoluteaiassignment.core.Route
import com.ronit.resoluteaiassignment.feature_auth.ui.signup.SignUpViewModel
import com.ronit.resoluteaiassignment.feature_auth.ui.signup.event.SignUpEvent


@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: SignUpViewModel
){

    val activity = LocalContext.current as Activity

    // Show password state
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
                horizontalArrangement = Arrangement.SpaceBetween,
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
                        .padding(end = 10.dp, bottom = 27.dp)
                        .clickable {
                           viewModel.onSignUpEventChanged(SignUpEvent.LoginEvent{
                               navController.navigate(Route.Login.route)
                           })
                        },
                    text = "Log In",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
            )

        }

        Spacer(modifier = Modifier.height(30.dp))

        TextField(
                modifier = Modifier
                    .padding(start = 30.dp, end = 30.dp)
                    .fillMaxWidth(),
                value =viewModel.signUpState.email,
                onValueChange = {email:String->
                    viewModel.onSignUpEventChanged(
                            SignUpEvent.EmailEntered(email)
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
                value =viewModel.signUpState.mobileNumber,
                onValueChange = {number:String->

                    // to limit mobile number to 10
                    if(number.length<=10)
                        viewModel.onSignUpEventChanged(
                                SignUpEvent.MobileNumberEntered(number)
                        )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, autoCorrect = false),
                label = { Text(text = "Enter Mobile Number")},
                placeholder = { Text(text = "Number")},
        )

        Spacer(
                modifier = Modifier.height(20.dp)
        )

        TextField(
                modifier = Modifier
                    .padding(start = 30.dp, end = 30.dp)
                    .fillMaxWidth(),
                value = viewModel.signUpState.password,
                visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                onValueChange = {password:String->

                    viewModel.onSignUpEventChanged(
                            SignUpEvent.PasswordEntered(password)
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
                    viewModel.onSignUpEventChanged(SignUpEvent.SignUp(activity){otp->
                        navController.navigate(Route.Verification.route)
                        viewModel.setReceivedOtp(otp)
                    })}
        ) {

            Text(
                    text = "Sign Up",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
            )
        }

    }

}