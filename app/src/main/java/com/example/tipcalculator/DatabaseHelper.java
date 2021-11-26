package com.example.tipcalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Path;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    SQLiteDatabase DB;
    public DatabaseHelper( Context context) {
        super(context, "RestaurantData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table RestaurantData(RestaurantID INTERGER primary key, score DOUBLE)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists RestaurantData");
    }

    public void Open() throws SQLException {

        DB = this.getWritableDatabase();
    }

    public void Close(){
        DB.close();
    }

    public Boolean insertData(int RestaurantID,double score ){
        Open();
        ContentValues contentValues = new ContentValues();
        contentValues.put("RestaurantID",RestaurantID);
        contentValues.put("score",score);
        long result = DB.insert("RestaurantData",null,contentValues);
        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    public Boolean updateData(int RestaurantID,double score ) {
        int location = 0;
        Open();
        Cursor cursor = DB.rawQuery("Select score from RestaurantData where RestaurantID=?", new String[]{String.valueOf(RestaurantID)});


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
                long result = DB.update("RestaurantData", contentValues, "RestaurantID=?", new String[]{String.valueOf(RestaurantID)});
                return true;
            }else{
                return false;
            }

    }




    public Cursor getData(){
        Open();
        Cursor cursor = DB.rawQuery("Select * from RestaurantData",null);
        return cursor;
    }

    public double getScores(int RestaurantID){
        Open();
        Cursor cursor = DB.rawQuery("Select score from RestaurantData where RestaurantID=?",new String[] {String.valueOf(RestaurantID)});
        if(cursor.moveToFirst()){
            double score = cursor.getDouble(0);
            return score;
        }

        return 0;
    }

    public boolean checkScore() {

            Open();
            Cursor cursor = DB.rawQuery("Select * from RestaurantData", null);
            if (cursor.moveToFirst()) {
                return true;
            }else{
                return false;
            }


    }
}
