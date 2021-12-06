package com.example.tipcalculator;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Globals g = Globals.getInstance();
        EditText zip = findViewById(R.id.editTextNumber);
        Button set_btn = findViewById(R.id.button8);
        set_btn.setOnClickListener(v -> g.zip = Integer.parseInt(zip.getText().toString()));
    }


}