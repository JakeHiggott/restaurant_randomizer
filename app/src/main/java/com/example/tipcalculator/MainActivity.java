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
        displayButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, findrestuarant.class)));
    }
    private void favorites() {
        Button displayButton = findViewById(R.id.goToFindRestaurant2);
        displayButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, favorites.class)));
    }
    private void settings() {
        Button displayButton = findViewById(R.id.settingsButton);
        displayButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, settings.class)));
    }
    /*
    private void init18Button() {
        Button displayButton = findViewById(R.id.button18);
        displayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editTextEnterTotal = findViewById(R.id.enterMoneyText);
                TextView tipAmountDisplay = findViewById(R.id.tipAmountText);
                String totalText = editTextEnterTotal.getText().toString();
                //Just a Quick note I was unsure how to change the value from a String to a Double/ Float to do calculations so I looked it up.
                //I Found the code below from here: https://www.quora.com/How-would-I-get-an-int-value-from-an-edit-text-view-in-Android-studio
                //It shows how to do it with integers but I just swapped integer for double and it works fine.
                double totalNumber = Double.parseDouble(totalText);
                double tipTotal = (totalNumber * 0.18);
                double totalNumberActual = totalNumber + tipTotal;
                //Rounded Doubles to 2 decimals to make it look better.
                String Display = String.format("Tip: $%.2f , Total Bill: $%.2f", tipTotal,totalNumberActual);
                tipAmountDisplay.setText(Display);
            }
        });
    }
    private void init20Button() {
        Button displayButton = findViewById(R.id.button20);
        displayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editTextEnterTotal = findViewById(R.id.enterMoneyText);
                TextView tipAmountDisplay = findViewById(R.id.tipAmountText);
                String totalText = editTextEnterTotal.getText().toString();
                //Just a Quick note I was unsure how to change the value from a String to a Double/ Float to do calculations so I looked it up.
                //I Found the code below from here: https://www.quora.com/How-would-I-get-an-int-value-from-an-edit-text-view-in-Android-studio
                //It shows how to do it with integers but I just swapped integer for double and it works fine.
                double totalNumber = Double.parseDouble(totalText);
                double tipTotal = (totalNumber * 0.20);
                double totalNumberActual = totalNumber + tipTotal;
                //Rounded Doubles to 2 decimals to make it look better.
                String Display = String.format("Tip: $%.2f , Total Bill: $%.2f", tipTotal,totalNumberActual);
                tipAmountDisplay.setText(Display);
            }
        });
    }
     */
}