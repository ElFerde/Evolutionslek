package com.example.evolutionslek;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Ingame extends AppCompatActivity {

    public static String ANIMAL = "djur";
    private String a;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingame);

        Intent intent = getIntent();
        String animal = intent.getStringExtra(StartScreen.ANIMAL);
        Animals djur = new Animals();
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
        a = Integer.toString(djur.mass);

    }
    public void showQR(View view){
        Intent intent = new Intent(this, ShowQR.class);
        intent.putExtra(ANIMAL, a);
        startActivity(intent);
    }
}
