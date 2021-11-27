package com.example.tipcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);
        favorites();
        findRestaurant();
        settings();


    }
    private void findRestaurant() {
        Button displayButton = findViewById(R.id.goToFindRestaurant);
        displayButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, findrestaurant.class)));
    }
    private void favorites() {
        Button displayButton = findViewById(R.id.goToFindRestaurant2);
        displayButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, favorites.class)));
    }
    private void settings() {
        Button displayButton = findViewById(R.id.settingsButton);
        displayButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, settings.class)));
    }

}
