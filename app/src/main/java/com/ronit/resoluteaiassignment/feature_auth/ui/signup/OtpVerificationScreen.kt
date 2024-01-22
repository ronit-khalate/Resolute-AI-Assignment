package com.ronit.resoluteaiassignment.feature_auth.ui.signup

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
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalInputModeManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ronit.resoluteaiassignment.core.Route
import com.ronit.resoluteaiassignment.feature_auth.ui.signup.event.VerificationEvent


@OptIn(ExperimentalComposeUiApi::class)

@Composable
fun OtpVerificationScreen(
    viewModel: SignUpViewModel,
    navController: NavController
){

    val firstDigitTextFieldFocusRequest = remember {
        FocusRequester()
    }

    val secondDigitTextFieldFocusRequest = remember {
        FocusRequester()
    }

    val thirdDigitTextFieldFocusRequest = remember {
        FocusRequester()
    }

    val fourthDigitTextFieldFocusRequest = remember {
        FocusRequester()
    }

    val fifthDigitTextFieldFocusRequest = remember {
        FocusRequester()
    }

    val sixthDigitTextFieldFocusRequest = remember {
        FocusRequester()
    }

    LaunchedEffect(key1 = Unit){

        firstDigitTextFieldFocusRequest.requestFocus()
    }



    val keyBoardOption=KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Number
    )

    Column (
            modifier = Modifier
                .fillMaxSize()
            ,
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
    ){

        Card(
                modifier = Modifier
                    .padding(top = 150.dp, start = 10.dp, end = 10.dp)
                    .fillMaxWidth()
                    .height(330.dp)

        ) {

            Column (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){

                Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, top = 30.dp),
                        horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                            text = "Verification",
                            fontWeight = FontWeight.Bold,
                            fontSize = 35.sp
                    )
                }
                Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, top = 10.dp),
                        horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                            text = buildAnnotatedString {
                                    append(
                                            "A 6-Digit PIN has been"+
                                            "sent to your phone number,"
                                    )
                                append("enter it below to continue")
                            },
                            fontSize = 16.sp
                    )
                }

                Spacer(modifier =Modifier.height(50.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    OutlinedTextField(
                            modifier = Modifier
                                .focusRequester(firstDigitTextFieldFocusRequest)
                                .height(50.dp)
                                .width(50.dp),
                            value =viewModel.verificationState.firstDigit,
                            keyboardOptions = keyBoardOption,
                            singleLine = true,
                            onValueChange = {
                                viewModel.onVerificationEventChanged(VerificationEvent.FirstDigitEntered(it)){
                                    secondDigitTextFieldFocusRequest.requestFocus()
                                }
                            }
                    )
                    OutlinedTextField(
                            modifier = Modifier
                                .focusRequester(secondDigitTextFieldFocusRequest)
                                .height(50.dp)
                                .width(50.dp),
                            value =viewModel.verificationState.secondDigit ,
                            keyboardOptions = keyBoardOption,
                            singleLine = true,
                            onValueChange = {
                                viewModel.onVerificationEventChanged(VerificationEvent.SecondDigitEntered(it)){
                                    thirdDigitTextFieldFocusRequest.requestFocus()
                                }
                            }
                    )
                    OutlinedTextField(
                            modifier = Modifier
                                .focusRequester(thirdDigitTextFieldFocusRequest)
                                .height(50.dp)
                                .width(50.dp),
                            value =viewModel.verificationState.thirdDigit ,
                            keyboardOptions = keyBoardOption,
                            singleLine = true,
                            onValueChange = {
                                viewModel.onVerificationEventChanged(VerificationEvent.ThirdDigitEntered(it)){
                                    fourthDigitTextFieldFocusRequest.requestFocus()
                                }
                            }
                    )
                    OutlinedTextField(
                            modifier = Modifier
                                .focusRequester(fourthDigitTextFieldFocusRequest)
                                .height(50.dp)
                                .width(50.dp),
                            value =viewModel.verificationState.fourthDigit ,
                            keyboardOptions = keyBoardOption,
                            singleLine = true,
                            onValueChange = {
                                viewModel.onVerificationEventChanged(VerificationEvent.FourthDigitEntered(it)){
                                    fifthDigitTextFieldFocusRequest.requestFocus()
                                }
                            }
                    )

                    OutlinedTextField(
                            modifier = Modifier
                                .focusRequester(fifthDigitTextFieldFocusRequest)
                                .height(50.dp)
                                .width(50.dp),
                            value =viewModel.verificationState.fifthDigit ,
                            keyboardOptions = keyBoardOption,
                            singleLine = true,
                            onValueChange = {
                                viewModel.onVerificationEventChanged(VerificationEvent.FifthDigitEntered(it)){
                                    sixthDigitTextFieldFocusRequest.requestFocus()
                                }
                            }
                    )
                    OutlinedTextField(
                            modifier = Modifier
                                .focusRequester(sixthDigitTextFieldFocusRequest)
                                .height(50.dp)
                                .width(50.dp),
                            value =viewModel.verificationState.sixthDigit ,
                            keyboardOptions = keyBoardOption,
                            singleLine = true,
                            onValueChange = {
                                viewModel.onVerificationEventChanged(VerificationEvent.SixthDigitEntered(it)){
                                    sixthDigitTextFieldFocusRequest.freeFocus()
                                    fifthDigitTextFieldFocusRequest.freeFocus()
                                }
                            }
                    )



                }

                Spacer(modifier = Modifier.height(30.dp))
                OutlinedButton(onClick = {
                    viewModel.onVerificationEventChanged(VerificationEvent.Verify {
                        navController.navigate(Route.Profile.route)
                    }) {}
                }) {
                    Text(text = "Verify", fontSize = 20.sp)
                }




            }
        }
    }
}


