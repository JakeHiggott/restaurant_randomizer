package com.example.tipcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);
        favorites();
        findRestuarant();
        settings();


    }
    private void findRestuarant() {
        Button displayButton = findViewById(R.id.goToFindRestuarant);
        displayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, findrestuarant.class));
            }
        });
    }
    private void favorites() {
        Button displayButton = findViewById(R.id.goToFindRestuarant2);
        displayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, favorites.class));
            }
        });
    }
    private void settings() {
        Button displayButton = findViewById(R.id.settingsButton);
        displayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, settings.class));
            }
        });
    }

}
