package com.example.tipcalculator;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        settingsBackButton();

    }
    private void settingsBackButton() {
        Button displayButton = findViewById(R.id.settingsBackButton);
        displayButton.setOnClickListener(view -> finish());
    }

}