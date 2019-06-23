package com.example.evolutionslek;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class Ingame extends AppCompatActivity {

    public static String ANIMAL = "djur";
    Animals djur = new Animals();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingame);

        Intent intent = getIntent();
        String animal = intent.getStringExtra(StartScreen.ANIMAL);
        switch (animal) {
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

    public void showQR(View view) {
        Intent intent = new Intent(this, ShowQR.class);
        intent.putExtra(ANIMAL, djur);
        startActivity(intent);
    }

    public void breed(View view) {
        Intent intent = new Intent(this, Breeding.class);
        intent.putExtra(ANIMAL, djur);
        startActivityForResult(intent, 1);
    }

    public void eat(View view) {
        Intent intent = new Intent(this, Eating.class);
        intent.putExtra(ANIMAL, djur);
        startActivityForResult(intent, 2);
    }

    public void die(View view) {
        Intent intent = new Intent(this, IsDead.class);
        startActivityForResult(intent, 3);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RESULT_OK){
            if(resultCode == 1){
                djur = data.getParcelableExtra("result");

            }

            if(resultCode == 2){
                djur.food += Integer.parseInt(data.getStringExtra("result"));
            }

            if(resultCode == 3){
                djur = data.getParcelableExtra("result");
            }
        }
    }
}