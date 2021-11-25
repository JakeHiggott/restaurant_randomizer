package com.example.tipcalculator;

import android.os.StrictMode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class API {

        ArrayList<Integer> RestaurantId = new ArrayList<>();
        ArrayList<String> RestaurantName = new ArrayList<>();
        ArrayList<Double> Restaurantlat = new ArrayList<>();
        ArrayList<Double> Restaurantlongi = new ArrayList<>();
        ArrayList<String> RestaurantPhoto = new ArrayList<>();


        public API(){
            double lat,longi;
            Globals g = Globals.getInstance();
            lat = g.getLatitude();
            longi = g.getLongitude();
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://travel-advisor.p.rapidapi.com/restaurants/list-by-latlng?latitude="+ lat +"&longitude=" + longi + "&distance=1&open_now=true&lunit=mi")
                    .get()
                    .addHeader("x-rapidapi-host", "travel-advisor.p.rapidapi.com")
                    .addHeader("x-rapidapi-key", "cd241becc4msh5b28c628534ff6cp123f66jsnafb8b78e56b4")
                    .build();


            try {
                Response response = client.newCall(request).execute();
                String obj = Objects.requireNonNull(response.body()).string();

                JSONObject JO = new JSONObject(obj);
                JSONArray DataArray = JO.getJSONArray("data");
                for (int i=0;i != DataArray.length();i++){
                    JSONObject RID = DataArray.getJSONObject(i);
                    JSONObject PhotoP = RID.getJSONObject("photo");
                    JSONObject imageP = PhotoP.getJSONObject("images");
                    JSONObject thumbP = imageP.getJSONObject("thumbnail");
                    RestaurantId.add(Integer.valueOf(RID.getString("location_id")));
                    RestaurantName.add(RID.getString("name"));
                    Restaurantlat.add(Double.valueOf(RID.getString("latitude")));
                    Restaurantlongi.add(Double.valueOf(RID.getString("longitude")));
                    RestaurantPhoto.add(thumbP.getString("url"));
                }
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }


            g.setRestaurantID(RestaurantId);
            g.setRestaurantName(RestaurantName);
            g.setRestaurantlat(Restaurantlat);
            g.setRestaurantlongi(Restaurantlongi);
            g.setRestaurantPhoto(RestaurantPhoto);

        }


    }

