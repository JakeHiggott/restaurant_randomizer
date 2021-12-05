package com.example.tipcalculator;

import java.util.ArrayList;

public class Globals {
    private static Globals instance;
    private ArrayList<Integer> RestaurantID = new ArrayList<>();
    private ArrayList<String> RestaurantName = new ArrayList<>();
    private ArrayList<String> RestaurantPhoto = new ArrayList<>();
    private ArrayList<Double> ChosenScores = new ArrayList<>();
    private ArrayList<Double> RestaurantLatitude = new ArrayList<>();
    private ArrayList<Double> RestaurantLongitude = new ArrayList<>();

    private double Longitude;
    private double Latitude;
    private int RandomIndex;
    public boolean BackButton = false;
    public String FavUrl;
    public String FavName;
    public int FavID;
    public double FavLat;
    public double FavLong;


    private Globals(){}
    //set global variables following set and get function pattern
    public void setRestaurantID(ArrayList<Integer> I){
        this.RestaurantID=I;
    }
    public ArrayList<Integer> getRestaurantID(){
        return this.RestaurantID;
    }

    public void setRestaurantName(ArrayList<String> restaurantName) {
        this.RestaurantName = restaurantName;
    }

    public void setChosenScores(ArrayList<Double> chosenScores) {
        ChosenScores = chosenScores;
    }

    public ArrayList<Double> getChosenScores() {
        return ChosenScores;
    }

    public ArrayList<String> getRestaurantName(){
        return this.RestaurantName;
    }

    public void setRestaurantlat(ArrayList<Double> restaurantlat){
        this.RestaurantLatitude = restaurantlat;
    }

    public void setRestaurantlongi(ArrayList<Double> restaurantlongi){
        this.RestaurantLongitude = restaurantlongi;
    }

    public ArrayList<Double> getRestaurantLatitude(){
        return RestaurantLatitude;
    }

    public ArrayList<Double> getRestaurantLongitude() {
        return RestaurantLongitude;
    }

    public void setRestaurantPhoto(ArrayList<String> p){
        this.RestaurantPhoto = p;
    }

    public ArrayList<String> getRestaurantPhoto() {
        return RestaurantPhoto;
    }

    public void setLatitude(double l){
        this.Latitude = l;
    }

    public double getLatitude(){
        return this.Latitude;
    }

    public void setLongitude(double l){
        this.Longitude = l;
    }

    public double getLongitude(){
        return this.Longitude;
    }

    public int getRandomIndex() {
        return RandomIndex;
    }

    public void setRandomIndex(int randomIndex) {
        this.RandomIndex = randomIndex;
    }

    public static synchronized Globals getInstance(){
        if(instance==null){
            instance=new Globals();
        }
        return instance;
    }
}
