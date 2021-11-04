package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class findrestuarant extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findrestuarant);
        FRBackButton();
        FRSearchButton();
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
    private void FRSearchButton() {
        Button displayButton = findViewById(R.id.frSearchButton);
        displayButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                new Calculate();
                startActivity(new Intent(findrestuarant.this, restuarantpage.class));
            }
        });
    }




}