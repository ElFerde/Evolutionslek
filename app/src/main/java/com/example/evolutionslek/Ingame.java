package com.example.evolutionslek;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class Ingame extends AppCompatActivity {

    public static String ANIMAL_MASS = "djur";
    Animals djur = new Animals();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingame);

        Intent intent = getIntent();
        String animal = intent.getStringExtra(StartScreen.ANIMAL);
        switch (animal){
            case "1":
                djur = new Husmus();
                break;
            case "2":
                djur = new Unicorn();
                break;
            case "3":
                djur = new Pedro();
                break;
            case "4":
                djur = new Gruffalo();
                break;
        }

    }
    public void showQR(View view){
        Intent intent = new Intent(this, ShowQR.class);
        intent.putExtra(ANIMAL_MASS, djur.mass);
        startActivity(intent);
    }
    public void breed(View view){
        Intent intent = new Intent(this, Breeding.class);
    }
    public void eat(View view){
        Intent intent = new Intent(this, Eating.class);
    }
    public void die(View view){
        Intent intent = new Intent(this, IsDead.class);
    }
}
