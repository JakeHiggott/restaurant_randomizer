package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
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
        int SelectedID = RIDs.get(g.getRandomIndex());
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

        Button SaveButton = findViewById(R.id.button5);
        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveRating(SelectedID);
            }
        });
        Button BackButton = findViewById(R.id.rateBackButton);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }

    void SaveRating(int selectedID){
        RatingBar Rating = findViewById(R.id.ratingBar2);
        DatabaseHelper DB = new DatabaseHelper(this);
        double score = 0;
        if(Rating.getRating()==1){
            score = -0.2;
        }
        if(Rating.getRating()==1){
            score = -0.1;
        }
        if(Rating.getRating()==1){
            score = 0;
        }
        if(Rating.getRating()==1){
            score = 0.1;
        }
        if(Rating.getRating()==1){
            score = 0.2;
        }

        if(DB.insertData(selectedID,score)){
            DB.updateData(selectedID,score);
        }

    }

}