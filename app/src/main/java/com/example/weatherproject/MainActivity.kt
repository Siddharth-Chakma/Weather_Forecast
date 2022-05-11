package com.example.weatherproject

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.example.weatherproject.databinding.ActivityMainBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var activityMainBinding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        supportActionBar?.hide()
        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this)
        activityMainBinding.rlMainLayout.visibility= View.GONE

        getCurrentLocation();
    }

    private fun getCurrentLocation()
    {
        if(checkPermissions())
        {
            if(isLocationEnabled())
            {
                if(ActivityCompat.checkSelfPermission(
                        this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ){
                    requestPermission()
                    return
                }
                fusedLocationProviderClient.lastLocation.addOnCompleteListener(this){ task->
                    val location: Location?=task.result
                    if(location==null)
                    {
                        Toast.makeText(this,"Null Received", Toast.LENGTH_SHORT).show()
                    }
                    else
                    {
                        /*fetch weather here*/
                        fetchCurrentLocationWeather(location.latitude.toString(),location.longitude.toString())
                    }

                }
            }
            else
            {
                Toast.makeText(this,"Turn on Location",Toast.LENGTH_SHORT).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)


            }
        }
        else
        {
            requestPermission()
        }
    }

    private fun fetchCurrentLocationWeather(latitude: String,longitude:String) {

    }


    private fun isLocationEnabled():Boolean{}

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION),
            
        )
    }

    companion object{
    }

    private fun checkPermissions():Boolean
    {}

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {}
}