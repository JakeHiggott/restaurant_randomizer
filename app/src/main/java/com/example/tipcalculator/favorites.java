package com.example.tipcalculator;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class favorites extends AppCompatActivity {

    private final ArrayList<Integer> mID = new ArrayList<>();
    private final ArrayList<String> mNames = new ArrayList<>();
    private final ArrayList<String> mImageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        refresh();
        initRecyclerView();

        Button refresh = findViewById(R.id.refreshButton);
        refresh.setOnClickListener(view -> {
            mID.clear();
            mNames.clear();
            mImageUrls.clear();
            refresh();
            initRecyclerView();
        });

        Button Back = findViewById(R.id.favoritesBackButton);
        Back.setOnClickListener(view -> finish());


    }


    private void initRecyclerView(){
        RecyclerView Rec = findViewById(R.id.RecView);
        Adapter adapter = new Adapter(this,mID,mNames,mImageUrls);
        Rec.setAdapter(adapter);
        Rec.setLayoutManager(new LinearLayoutManager(this));
    }

    public void refresh(){
        DatabaseHelper DB = new DatabaseHelper(this);
        Cursor cursor = DB.getFavorites();

        if(cursor != null){

            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
                @SuppressLint("Range") int ID = cursor.getInt(cursor.getColumnIndex("RestaurantID"));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range") String Image = cursor.getString(cursor.getColumnIndex("photo"));
                mID.add(ID);
                mNames.add(name);
                mImageUrls.add(Image);

            }
        }
    }

}