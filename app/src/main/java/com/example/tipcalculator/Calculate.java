package com.example.tipcalculator;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationRequest;
import android.renderscript.RenderScript;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Calculate extends AppCompatActivity {
    //initialize variables
    FusedLocationProviderClient fusedLocationProviderClient;
    double currentLat;
    double currentLong;


    public Calculate() {
        //Initialize fusedlocationclient
        Log.d("Before it","Before it");
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(Calculate.this);
        Log.d("Made it","Made it");

        //check permissions
        if (ActivityCompat.checkSelfPermission(Calculate.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(Calculate.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            GetLocation();

        } else {
            Log.d("No Permission","Permissions missing");
            ActivityCompat.requestPermissions(Calculate.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    44);
        }

    }


    @SuppressLint("MissingPermission")
    private void GetLocation() {
        Log.d("Reached Search", "GET LOCATION");
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
           fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
               @Override
               public void onComplete(@NonNull Task<Location> task) {
                   Location location = task.getResult();
                   if(location != null){
                       currentLat = location.getLatitude();
                       currentLong = location.getLongitude();
                   }
               }
           });
        }/*else{
            LocationRequest locationRequest = new LocationRequest()
                    .setPriority(LocationRequest.QUALITY_BALANCED_POWER_ACCURACY)
                    .setInterval(10000)
                    .setFastestInterval(1000)
                    .setNumUpdates(1);

            LocationCallback locationCallback = new LocationCallback() {
                @Override
                public void onLocationResult(@NonNull LocationResult locationResult) {
                    Location location1 = locationResult.getLastLocation();

                }
            };

        }*/

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
