package com.example.evolutionslek;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/*
 * Called by: The app starts here
 *
 * Purpose: nothing so far
 *
 * Possible future improvements: different start buttons for different playmodes?
 */

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void start(View view){
        Intent intent = new Intent(this,StartScreen.class);
        startActivity(intent);
    }
}