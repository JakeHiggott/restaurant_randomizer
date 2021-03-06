package com.example.tipcalculator;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    SQLiteDatabase DB;
    public DatabaseHelper( Context context) {
        super(context, "RestaurantData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table RestaurantData(RestaurantID INTEGER primary key,name STRING,score DOUBLE, inFavorites INTEGER, photo STRING, rLAT DOUBLE, rLONG DOUBLE)"); //Uses SQL to create the database if it doesn't exist
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists RestaurantData");
    }

    public void Open() throws SQLException {                                                        //Opens the database for read or write, is getwritable because that has the most privileges
        DB = this.getWritableDatabase();
    }

    public void insertData(int RestaurantID, double score ){  //Uses the restaurantID from the API to insert a new score into the database
        Open();
        ContentValues contentValues = new ContentValues();
        contentValues.put("RestaurantID",RestaurantID);
        contentValues.put("score",score);
        DB.insert("RestaurantData",null,contentValues);

    }

    @SuppressLint("Recycle")
    public Boolean updateData(int RestaurantID, double score ) { //uses RestaurantID to increase or decrease the rating from the user
        int location;
        Open();
        @SuppressLint("Recycle") Cursor cursor = DB.rawQuery("Select score from RestaurantData where RestaurantID=?", new String[]{String.valueOf(RestaurantID)});


            if(cursor.moveToFirst()){
                location = cursor.getPosition();
            }
            else {
                return false;
            }
            double NewScore = cursor.getDouble(location) + score;
            ContentValues contentValues = new ContentValues();
            contentValues.put("score", NewScore);
            cursor = DB.rawQuery("Select * from RestaurantData where RestaurantID=?", new String[]{String.valueOf(RestaurantID)});
            if (cursor.getCount() > 0) {
                DB.update("RestaurantData", contentValues, "RestaurantID=?", new String[]{String.valueOf(RestaurantID)});
                return true;
            }else{
                return false;
            }

    }

    public double getScores(int RestaurantID){  //gets the score that match a RestaurantID if one exists
        Open();
        @SuppressLint("Recycle") Cursor cursor = DB.rawQuery("Select score from RestaurantData where RestaurantID=?",new String[] {String.valueOf(RestaurantID)});
        if(cursor.moveToFirst()){
            return cursor.getDouble(0);
        }

        return 0;
    }

    public void addFavorites(int RestaurantID , String name , String photo, Double Lat, Double longi){
        Open();
        @SuppressLint("Recycle") Cursor cursor = DB.rawQuery("Select * from RestaurantData where RestaurantID=?",new String[] {String.valueOf(RestaurantID)});
        ContentValues contentValues = new ContentValues();
        contentValues.put("inFavorites",1);
        contentValues.put("RestaurantID",RestaurantID);
        contentValues.put("name",name);
        contentValues.put("photo", photo);
        contentValues.put("rLAT", Lat);
        contentValues.put("rLONG", longi);
        if(cursor.getCount() > 0){
            DB.update("RestaurantData",contentValues,"RestaurantID=?", new String[]{String.valueOf(RestaurantID)});
        }else{

            long result = DB.insert("RestaurantData",null,contentValues);
            if (result == -1){
                Log.e(null,"Insert Failed");
            }
        }
    }

    public void removeFavorites(int RestaurantID){
        Open();
        @SuppressLint("Recycle") Cursor cursor = DB.rawQuery("Select * from RestaurantData where RestaurantID=?",new String[] {String.valueOf(RestaurantID)});
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            ContentValues contentValues = new ContentValues();
            contentValues.put("inFavorites",0);
            DB.update("RestaurantData",contentValues,"RestaurantID=?", new String[]{String.valueOf(RestaurantID)});
        }
    }

    public Cursor getFavorites(){
        Open();
        Cursor cursor = DB.rawQuery("Select * from RestaurantData where inFavorites=?", new String[] {String.valueOf(1)});
        if(cursor.getCount() > 0){
            return cursor;
        }else{
            return null;
        }
    }

}
