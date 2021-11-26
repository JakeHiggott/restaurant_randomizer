package com.example.tipcalculator;

import java.util.ArrayList;
import java.util.List;

public class Globals {
    private static Globals instance;
    private ArrayList<Integer> RestaurantID;
    private ArrayList<String> RestaurantName;
    private ArrayList<Double> Restaurantlat;
    private ArrayList<Double> Restaurantlongi;
    private ArrayList<String> RestaurantPhoto;
    private ArrayList<Double> ChoosenScores;
    private double Longitude;
    private double Latitude;
    private int RandomIndex;
    public boolean BackButton = false;


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

    public void setChoosenScores(ArrayList<Double> choosenScores) {
        ChoosenScores = choosenScores;
    }

    public ArrayList<Double> getChoosenScores() {
        return ChoosenScores;
    }

    public ArrayList<String> getRestaurantName(){
        return this.RestaurantName;
    }

    public void setRestaurantlat(ArrayList<Double> l){
        this.Restaurantlat = l;
    }

    public ArrayList<Double> getRestaurant(){
        return this.Restaurantlat;
    }

    public void setRestaurantlongi(ArrayList<Double> l){
        this.Restaurantlongi = l;
    }

    public List<Double> getRestaurantlongi(){
        return this.Restaurantlongi;
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
