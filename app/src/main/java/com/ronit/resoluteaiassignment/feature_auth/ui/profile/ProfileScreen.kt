package com.ronit.resoluteaiassignment.feature_auth.ui.profile

import android.Manifest
import android.content.pm.PackageManager
import android.location.LocationProvider
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.ktx.model.markerOptions


@Preview(showBackground = true)
@Composable
fun ProfileScreen() {

    val locationProvider = LocationServices.getFusedLocationProviderClient(LocalContext.current)
    val viewModel:ProfileViewModel = hiltViewModel()
    LaunchedEffect(key1 = Unit){
        viewModel.getUserCurrentLocation(locationProvider)
    }
    var uiSettings by remember { mutableStateOf(MapUiSettings(myLocationButtonEnabled = true)) }
    val properties by remember {
        mutableStateOf(MapProperties(isMyLocationEnabled = true, mapType = MapType.NORMAL))
    }







    Column(

            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 30.dp),
            verticalAlignment = Alignment.CenterVertically,
        ){

            Text(text = "Name :-\t", fontSize = 20.sp)
            Text(text ="Ronit", fontSize = 20.sp)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 30.dp),
            verticalAlignment = Alignment.CenterVertically,
        ){

            Text(text = "Email :-\t", fontSize = 20.sp)
            Text(text ="${Firebase.auth.currentUser!!.email}", fontSize = 20.sp)
        }

        Spacer(modifier=Modifier.height(40.dp))

        val singapore = LatLng(1.35, 103.87)

        viewModel.currentLocation?.let {

            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(it, 10f)
            }


            GoogleMap(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp),
                    cameraPositionState =cameraPositionState,
                    properties =properties,
                    uiSettings = uiSettings

            ) {


              Marker(
                      state = MarkerState(position = it),
                      title = "Your Location",
                      snippet = "Marker in Your Location"
              )


            }
        }
    }

}