package com.example.tipcalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class restaurantpage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restuarantpage);
        Globals g = Globals.getInstance();

        TextView rName = findViewById(R.id.rName);
        ImageView rImage = findViewById(R.id.imageView2);

        rName.setText(g.FavName);
        Bitmap bm = null;

        //try to establish a connection with an internet resource through its URL
        try {
            URL url = new URL(g.FavUrl);
            URLConnection connection = url.openConnection();
            InputStream in = connection.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(in);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        rImage.setImageBitmap(bm);
        
        //building buttons for additional functionality
        Button backButton = findViewById(R.id.rpBackButton);
        backButton.setOnClickListener(view -> finish());

        Button removeFav = findViewById(R.id.RemoveFav);
        removeFav.setOnClickListener(view -> {
            DatabaseHelper DB = new DatabaseHelper(restaurantpage.this);
            Globals g1 = Globals.getInstance();

            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            @SuppressLint("InflateParams") View popup = inflater.inflate(R.layout.remove_confim,null);

            //show pop-up for better visibility for the UI
            final PopupWindow popupWindow = new PopupWindow(popup,800,600, false);

            popupWindow.showAtLocation(view, Gravity.CENTER,0,0);


            //create yes and no buttons for streamlined functionality
            Button yes =  popup.findViewById(R.id.yesButton);
            yes.setOnClickListener(view1 -> {
                DB.removeFavorites(g1.FavID);
                finish();
                finish();

            });

            Button no = popup.findViewById(R.id.noButton);
            no.setOnClickListener(view12 -> finish());



        });

        Button launch_btn = findViewById(R.id.launch);
        launch_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri navigationIntentUri = Uri.parse("google.navigation:q=" + g.FavLat + "," + g.FavLong);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, navigationIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

    }




}
