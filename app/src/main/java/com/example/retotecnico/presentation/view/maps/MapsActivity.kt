package com.example.retotecnico.presentation.view.maps

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.retotecnico.R
import com.example.retotecnico.core.Constants
import com.example.retotecnico.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.retotecnico.core.LocationUser

open class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private val viewModelMaps : ViewModelMaps by viewModels()
    lateinit var latLongActual: LatLng
    lateinit var mapFragment: SupportMapFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        requestGps()
        fnStartLocalization()
        val latitude = intent.getStringExtra(Constants.LATITUDE)?: ""
        val longitude = intent.getStringExtra(Constants.LONGITUDE)?: ""
        locateStore(LocationUser(latitude, longitude))
        updateLocation()
    }

    fun updateLocation(){
        viewModelMaps.updateLocation_.observe(this){ itLocation ->
            latLongActual = LatLng(itLocation.latitude.toDouble(), itLocation.longitude.toDouble())
            (mapFragment as? SupportMapFragment)?.getMapAsync(this)

        }
    }


    fun fnStartLocalization(){
        val mlocManager = getSystemService(LOCATION_SERVICE) as LocationManager
        val objLoca: UbicationLocalization = UbicationLocalization()
        objLoca.viewModelMaps = viewModelMaps
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            mlocManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                Constants.MIN_TIEMPO_ENTRE_UPDATES,
                Constants.MIN_CAMBIO_DISTANCIA_PARA_UPDATES,
                objLoca
            )
        }
    }

    fun requestGps(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION),
                1000
            )
        }
    }

    fun locateStore(location: com.example.retotecnico.core.LocationUser){
        val lati = location.latitude.toDouble()
        val longi = location.longitude.toDouble()
        val sydney = LatLng(lati, longi)

        val marker = MarkerOptions()
        val nameStore = intent.getStringExtra(Constants.NAME)
        marker.position(sydney).title(nameStore).visible(true)
        mMap.addMarker(marker)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        val tienda = LatLng(-12.066289, -77.036838) // -12.066289, -77.036838 LIMA CENTRO

        if (latLongActual == null) {
            mMap.moveCamera(CameraUpdateFactory.newLatLng(tienda))
        } else {
            mMap.moveCamera(
                CameraUpdateFactory.newLatLng(latLongActual)
            )
        }

        mMap.animateCamera(CameraUpdateFactory.zoomTo(16f))
    }

    class UbicationLocalization : LocationListener{

        var viewModelMaps : ViewModelMaps? = null

        override fun onLocationChanged(location: Location) {
            val latitud = location.getLatitude().toString()
            val longitud = location.getLongitude().toString()
            viewModelMaps?.updateLocation(com.example.retotecnico.core.LocationUser(latitud, longitud))
        }

    }
}