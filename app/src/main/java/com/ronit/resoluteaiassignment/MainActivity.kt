package com.ronit.resoluteaiassignment

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ronit.resoluteaiassignment.core.Route
import com.ronit.resoluteaiassignment.feature_auth.ui.login.LoginScreen
import com.ronit.resoluteaiassignment.feature_auth.ui.login.SignUpScreen
import com.ronit.resoluteaiassignment.feature_auth.ui.profile.ProfileScreen
import com.ronit.resoluteaiassignment.feature_auth.ui.signup.OtpVerificationScreen
import com.ronit.resoluteaiassignment.feature_auth.ui.signup.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth=Firebase.auth
        auth.signOut()

        
        setContent {
            val signUpViewModel :SignUpViewModel= viewModel()
            val navController= rememberNavController()

            val context = LocalContext.current
            val permissions = arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
            )

            val launcher = rememberLauncherForActivityResult(contract =ActivityResultContracts.RequestMultiplePermissions()){permissionMap->

                val areGranted = permissionMap.values.reduce{acc,next-> acc&& next}



            }

            LaunchedEffect(key1 = Unit){

                if(permissions.all {
                        ContextCompat.checkSelfPermission(
                                context,
                                it
                        )!= PackageManager.PERMISSION_GRANTED
                    }){
                    launcher.launch(permissions)
                }
            }


            NavHost(
                    navController = navController,
                    startDestination = if(auth.currentUser!=null) Route.Profile.route else Route.Login.route
            ){

                composable(route = Route.Login.route){
                    LoginScreen(navController)
                }

                composable(route= Route.SignUp.route){
                    SignUpScreen(navController,signUpViewModel)
                }
                
                composable(route=Route.Verification.route) {

                    OtpVerificationScreen(
                            viewModel = signUpViewModel,
                            navController = navController
                    )
                }

                composable(Route.Profile.route){

                    ProfileScreen()
                }
            }
        }
    }
}

