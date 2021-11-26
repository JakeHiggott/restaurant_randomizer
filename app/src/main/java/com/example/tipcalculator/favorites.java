package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class favorites extends AppCompatActivity {

    Globals g = Globals.getInstance();
    private ArrayList<String> mNames = new ArrayList<>();
    private  ArrayList<String> mImageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        DatabaseHelper DB = new DatabaseHelper(this);
        Cursor cursor = DB.getFavorites();

        if(cursor != null){

            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
               @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
               @SuppressLint("Range") String Image = cursor.getString(cursor.getColumnIndex("photo"));
               mNames.add(name);
               mImageUrls.add(Image);

            }
        }

        initRecyclerView();

        Button Back = findViewById(R.id.favoritesBackButton);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    private void initRecyclerView(){
        RecyclerView Rec = findViewById(R.id.RecView);
        Adapter adapter = new Adapter(this,mNames,mImageUrls);
        Rec.setAdapter(adapter);
        Rec.setLayoutManager(new LinearLayoutManager(this));
    }

}