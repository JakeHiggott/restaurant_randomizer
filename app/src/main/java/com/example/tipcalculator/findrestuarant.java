package com.example.tipcalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class findrestuarant extends AppCompatActivity {

    Switch switch1;
    TextView textView5,textView12,textView13;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findrestuarant);

        switch1 = findViewById(R.id.switch1);
        textView5 = findViewById(R.id.textView5);
        textView12 = findViewById(R.id.textView12);
        textView13 = findViewById(R.id.textView13);
        Button button = findViewById(R.id.frSearchButton);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);


        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ButtonCheck();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean switchState = switch1.isChecked();
                if(switchState == true){
                    if(ActivityCompat.checkSelfPermission(findrestuarant.this,
                            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                        getLocation();
                    }else{
                        ActivityCompat.requestPermissions(findrestuarant.this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
                    }

                }else{
                    textView12.setText("Custom");
                    textView13.setText("Custom");
                }
            }
        });

        FRBackButton();
    }

    @SuppressLint("MissingPermission")
    private void getLocation() {
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if(location != null){

                    try {
                        Geocoder geocoder = new Geocoder(findrestuarant.this, Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        textView12.setText("TEMPORARY Latitude: " + addresses.get(0).getLatitude());
                        textView13.setText("TEMPORARY Longitude: " + addresses.get(0).getLongitude());

                        new Zomato();
                        Zomato.Search(addresses.get(0).getLatitude(),addresses.get(0).getLongitude());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void ButtonCheck() {
        Boolean switchState = switch1.isChecked();
        if(switchState == true){
            textView5.setText("Using device's location");
        }else{
            textView5.setText("Using custom location");
        }
    }

    private void FRBackButton() {
        Button displayButton = findViewById(R.id.frBackButton);
        displayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }





}