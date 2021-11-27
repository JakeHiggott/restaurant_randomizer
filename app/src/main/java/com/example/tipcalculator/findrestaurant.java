package com.example.tipcalculator;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationRequest;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class findrestaurant extends AppCompatActivity {

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch switch1;
    TextView textView5,textView12,textView13,loadingTextView;
    FusedLocationProviderClient fusedLocationProviderClient;
    Location MockLocation;
    Globals g = Globals.getInstance();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findrestuarant);

        switch1 = findViewById(R.id.switch1);
        textView5 = findViewById(R.id.textView5);
        loadingTextView = findViewById(R.id.loadingTextView);
        Button button = findViewById(R.id.frSearchButton);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);


        switch1.setOnClickListener(view -> ButtonCheck());

        button.setOnClickListener(view -> {
            boolean switchState = switch1.isChecked();
            if(switchState){
                if(ActivityCompat.checkSelfPermission(findrestaurant.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                    getLocation();
                }else{
                    ActivityCompat.requestPermissions(findrestaurant.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
                }

            }else{
                /* the start of setting custom location
                fusedLocationProviderClient.setMockMode(true);
                Geocoder geocoder = new Geocoder(findrestaurant.this,Locale.getDefault());
                List<Address> addresses = geocoder.
                fusedLocationProviderClient.setMockLocation(MockLocation);*/
                textView12.setText("Custom");
                textView13.setText("Custom");
            }
        });

        FRBackButton();
    }

    @SuppressLint({"MissingPermission", "InlinedApi"})
    private void getLocation() {
        loadingTextView.setVisibility(TextView.VISIBLE);
        fusedLocationProviderClient.getCurrentLocation(LocationRequest.QUALITY_HIGH_ACCURACY, null).addOnCompleteListener(task -> {
            Location location = task.getResult();
            if(TryLocation(location) == null){
                fusedLocationProviderClient.getLastLocation().addOnCompleteListener(task1 -> {
                    Location location1 = task1.getResult();
                    TryLocation(location1);
                });
            }
            loadingTextView.setVisibility(TextView.INVISIBLE);
        }).addOnFailureListener(e -> loadingTextView.setVisibility(TextView.INVISIBLE));

    }

    private Object TryLocation(Location location){

        if(location != null){

            try {
                Geocoder geocoder = new Geocoder(findrestaurant.this, Locale.getDefault());
                List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                g.setLatitude(addresses.get(0).getLatitude());
                g.setLongitude(addresses.get(0).getLongitude());
                new API();
                GetSavedScores();
                new Randomizer();
                startActivity(new Intent(findrestaurant.this, rate.class));
                if(!g.BackButton){
                    finish();
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            return null;
        }

    return 0;
    }

    private void GetSavedScores() {
        DatabaseHelper DB = new DatabaseHelper(this);
        ArrayList<Integer> scores = g.getRestaurantID();
        ArrayList<Double> set = new ArrayList<>();

        for(int i =0; i != scores.size();i++){
            set.add(DB.getScores(scores.get(i))) ;
        }
        g.setChosenScores(set);

    }

    @SuppressLint("SetTextI18n")
    private void ButtonCheck() {
        boolean switchState = switch1.isChecked();
        if(switchState){
            textView5.setText("Using device's location");
        }else{
            textView5.setText("Using custom location");
        }
    }

    private void FRBackButton() {
        Button displayButton = findViewById(R.id.frBackButton);
        displayButton.setOnClickListener(view -> finish());
    }





}