package com.example.evolutionslek;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
    }

    private void animal1Chosen(View view){
        Intent intent = new Intent(this, Ingame.class);
        String animal = "Husmus";
        intent.putExtra(animal, "Husmus" );
    }
}