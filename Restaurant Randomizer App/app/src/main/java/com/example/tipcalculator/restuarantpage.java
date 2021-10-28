package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class restuarantpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restuarantpage);
        RPBackButton();
        RPRateButton();
    }
    private void RPBackButton() {
        Button displayButton = findViewById(R.id.rpBackButton);
        displayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void RPRateButton() {
        Button displayButton = findViewById(R.id.rpRateButton);
        displayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(restuarantpage.this, rate.class));
            }
        });
    }
}