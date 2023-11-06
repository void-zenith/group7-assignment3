package com.example.zenithrajbhandari_mapd711_assignment3

import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationRequest
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Marker

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private var latitude: Double = 0.toDouble()
    private var longitude: Double = 0.toDouble()

    private lateinit var previousLocation:Location
    private var markers:Marker? = null

    // location
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    lateinit var locationCallBack: LocationCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as?
                SupportMapFragment
        mapFragment?.getMapAsync(this)


    }


    override fun onMapReady(googleMap: GoogleMap) {
        val selectedCity = intent.getStringExtra("selectedCity")
        val geocoder = Geocoder(applicationContext)
        val geocodeResults =
            geocoder.getFromLocationName(selectedCity!!, 1)
        if(geocodeResults != null){
            val locations: Iterator<Address> = geocodeResults.iterator()
        }
    }
}