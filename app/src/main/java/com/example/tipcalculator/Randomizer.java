package com.example.tipcalculator;



import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;


public class Randomizer extends AppCompatActivity {
    Randomizer(){
        Globals g = Globals.getInstance();
        ArrayList<Integer> Selected =  g.getRestaurantID();
        ArrayList<Double> weights = new ArrayList<>();
        ArrayList<Double> Saved = g.getChosenScores();

        double check = 0;
        int size = Selected.size();

        if(size > 0) {
            double OChance = 100 / size;
            for (int i = 0; i != Selected.size(); i++) {
                double scale = OChance * Saved.get(i);
                double NChance = OChance + scale;
                weights.add(NChance);
                check = NChance + check;
            }


            if (check != 100) {
                double evening = 100 - check;
                double error = evening / size;
                for (int j = 0; j != size; j++) {
                    weights.set(j, (weights.get(j) + error));
                }
            }

            int SelectedIndex = ChooseRandom(weights);
            g.setRandomIndex(SelectedIndex);
        }else{
            Log.d(null,"RestaurantID size is 0");
        }



    }

    private int ChooseRandom(ArrayList<Double> weights) {

        int indexSelection;
        int index = 0;
        Random rand = new Random();
        double RandNum = rand.nextInt(101);
        double AddAmount=0;

        BigDecimal Ran = new BigDecimal(String.valueOf(RandNum));


        BigDecimal adding = new BigDecimal(String.valueOf(AddAmount));
        BigDecimal point = new BigDecimal("0.1");


        for(BigDecimal k = BigDecimal.valueOf(1); !k.equals(BigDecimal.valueOf(101)) ; k = k.add(point)){
            BigDecimal weight = new BigDecimal(String.valueOf(weights.get(index)));
            if(k.equals(Ran)){
                indexSelection = index;
                return indexSelection;
            }
            if(k.equals(weight.add(adding))){
                adding = adding.add(weight);
                index++;
            }
        }

        indexSelection = rand.nextInt(weights.size());
        return indexSelection;
    }



}
