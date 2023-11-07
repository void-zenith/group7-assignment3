package com.example.zenithrajbhandari_mapd711_assignment3

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as?
                SupportMapFragment
        mapFragment?.getMapAsync(this)
        val selectedCity = intent.getStringExtra("selectedCity")
        Log.d("city", selectedCity.toString())
        val actionBar = supportActionBar
        actionBar?.setTitle("Choose a Pizza Store!");
    }
    override fun onMapReady(googleMap: GoogleMap) {
        //enable zoom control
        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.uiSettings.isCompassEnabled = true
        val selectedCity = intent.getStringExtra("selectedCity")
        Log.d("city", selectedCity.toString())
        val geocoder = Geocoder(applicationContext)
        val geocodeResults =
            geocoder.getFromLocationName(selectedCity!!, 1)

        val allRestaurant = getRestaurantLocation(selectedCity)
        if (allRestaurant != null) {
            for (rest in allRestaurant) {
                var restLocation = LatLng(rest.latitude!!, rest.longitude!!)
                googleMap.addMarker(
                    MarkerOptions()
                        .position(restLocation)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.restaurant))
                        .title(selectedCity!!)
                )
            }
        }
        if (geocodeResults != null) {
            val lat = geocodeResults?.get(0)?.latitude?.toDouble()
            val log = geocodeResults?.get(0)?.longitude?.toDouble()
            val selectedCityPosition = LatLng(lat!!, log!!)
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(selectedCityPosition, 15.0f))
            googleMap.addMarker(
                MarkerOptions()
                    .position(selectedCityPosition)
                    .title(selectedCity!!)
            )
        }
    }
    private fun getRestaurantLocation(city: String): Array<LatLng>? {
        val cityCoordinates = mapOf(
            "Toronto Downtown" to arrayOf(
                LatLng(43.6549661, -79.4011184),
                LatLng(43.6549622, -79.4022)
            ),
            "Scarborough" to arrayOf(
                LatLng(43.7764, -79.2318),
                LatLng(43.7600, -79.2200)
            ),
            "Mississauga" to arrayOf(
                LatLng(43.5890, -79.6441),
                LatLng(43.5888, -79.6444)
            ),
            "Oakville" to arrayOf(
                LatLng(43.4675, -79.6877),
                LatLng(43.4666, -79.6866)
            ),
            "North York" to arrayOf(
                LatLng(43.7615, -79.4111),
                LatLng(43.7611, -79.4000)
            )
        )
        return cityCoordinates[city]
    }
}