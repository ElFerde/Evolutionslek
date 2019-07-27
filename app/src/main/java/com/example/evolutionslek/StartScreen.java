package com.example.evolutionslek;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartScreen extends AppCompatActivity {
    public static String ANIMAL = "animal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
    }

    public void createAnimal1(View view){
        Intent intent = new Intent(this, Ingame.class);
        intent.putExtra(ANIMAL, "1" );
        startActivity(intent);
    }
    public void createAnimal2(View view){
        Intent intent = new Intent(this, Ingame.class);
        intent.putExtra(ANIMAL, "2" );
        startActivity(intent);
    }
    public void createAnimal3(View view){
        Intent intent = new Intent(this, Ingame.class);
        intent.putExtra(ANIMAL, "3" );
        startActivity(intent);
    }
    public void createAnimal4(View view){
        Intent intent = new Intent(this, Ingame.class);
        intent.putExtra(ANIMAL, "4" );
        startActivity(intent);
    }
}