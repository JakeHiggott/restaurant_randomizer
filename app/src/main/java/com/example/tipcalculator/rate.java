package com.example.tipcalculator;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class rate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rate);
        Globals g = Globals.getInstance();
        TextView Name = findViewById(R.id.textView3);
        ImageView pic = findViewById(R.id.imageView);
        ArrayList<String> image = g.getRestaurantPhoto();
        ArrayList<String> AllName = g.getRestaurantName();
        ArrayList<Integer> RIDs = g.getRestaurantID();
        ArrayList<Double> aLat = g.getRestaurantLatitude();
        ArrayList<Double> aLong = g.getRestaurantLongitude();
        int SelectedID = RIDs.get(g.getRandomIndex());
        String imageUrl = image.get(g.getRandomIndex());
        String name = AllName.get(g.getRandomIndex());
        Double rLat = aLat.get(g.getRandomIndex());
        Double rLong = aLong.get(g.getRandomIndex());
        Bitmap bm = null;

        try {
            URL url = new URL(image.get(g.getRandomIndex()));
            URLConnection connection = url.openConnection();
            InputStream in = connection.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(in);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        pic.setImageBitmap(bm);
        Name.setText(AllName.get(g.getRandomIndex()));
        RatingBar Rating = findViewById(R.id.ratingBar2);
        Rating.setRating(3);

        Button SaveButton = findViewById(R.id.button5);
        SaveButton.setOnClickListener(view -> {
            SaveRating(SelectedID);
            finish();
        });

        Button Favorites = findViewById(R.id.FavoritesButton);
        Favorites.setOnClickListener(view -> addToFavorites(SelectedID,name,imageUrl,rLat,rLong));

        Button Directions = findViewById(R.id.button);
        Directions.setOnClickListener(v -> {
            Uri navigationIntentUri = Uri.parse("google.navigation:q=" + rLat + "," + rLong);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, navigationIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        });

    }

    private void addToFavorites(int selectedID, String name, String imageUrl, Double rLat, Double rLong) {
        DatabaseHelper DB = new DatabaseHelper(this);
        DB.addFavorites(selectedID, name, imageUrl, rLat, rLong);
    }

    void SaveRating(int selectedID){
        RatingBar Rating = findViewById(R.id.ratingBar2);
        DatabaseHelper DB = new DatabaseHelper(this);
        double score = 0;
        if(Rating.getRating()==1){
            score = -0.2;
        }else if(Rating.getRating()==2){
            score = -0.1;
        }else if(Rating.getRating()==3){
            score = 0;
        }else if(Rating.getRating()==4){
            score = 0.1;
        }else if(Rating.getRating()==5){
            score = 0.2;
        }

        if(!DB.updateData(selectedID,score)){

            DB.insertData(selectedID,score);
        }
        DB.close();
    }

}