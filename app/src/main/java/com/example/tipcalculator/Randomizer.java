package com.example.tipcalculator;



import android.app.Activity;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;


public class Randomizer extends AppCompatActivity {
    Randomizer(){
        Globals g = Globals.getInstance();
        ArrayList<Integer> Selected =  g.getRestaurantID();
        ArrayList<Double> weights = null;
        DatabaseHelper DB = new DatabaseHelper(this);
        double check = 0;
        int size = Selected.size();

        double OChance = 100/size;
        for(int i = 0; i != Selected.size();i++){
            if(DB.checkScore(Selected.get(i))){
                double scale = OChance* DB.getScore(Selected.get(i));
                double NChance = OChance + scale;
                weights.add(i,NChance);
                check = NChance + check;
            }
            else{
                check = OChance;
            }
        }

        if(check != 100){
           double evening = 100-check;
           double error = evening/size;
           for(int j = 0; j != size; j++){
               weights.set(j,(weights.get(j) + error));
           }
        }

        int SelectedIndex = ChooseRandom(weights);
        g.setRandomIndex(SelectedIndex);



        startActivity(new Intent(this, rate.class));
    }

    private int ChooseRandom(ArrayList<Double> weights) {

        int indexSelection = 1;
        int index = 1;
        Random rand = new Random();
        int RandNum = rand.nextInt(101);
        double AddAmount=0;

        for(int k=0;k != 100; k++){
            if(RandNum == k){
                indexSelection = index;
                return indexSelection;
            }
            if(k == weights.get(index) + AddAmount){
                AddAmount = weights.get(index) + AddAmount;
                index++;
            }
        }

        indexSelection = rand.nextInt(weights.size());
        return indexSelection;
    }



}
