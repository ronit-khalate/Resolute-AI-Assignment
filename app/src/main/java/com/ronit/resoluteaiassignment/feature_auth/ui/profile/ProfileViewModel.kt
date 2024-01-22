package com.ronit.resoluteaiassignment.feature_auth.ui.profile

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject

class ProfileViewModel @Inject constructor():ViewModel() {


    var currentLocation by mutableStateOf<LatLng?>(null)
        private set

    @SuppressLint("MissingPermission")
    fun getUserCurrentLocation(locationProvider:FusedLocationProviderClient){

        locationProvider.lastLocation.addOnSuccessListener {
            currentLocation = LatLng(it.latitude,it.longitude)
        }
    }
}