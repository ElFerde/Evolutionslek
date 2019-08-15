package com.example.evolutionslek;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/*
 * Called by: InGame
 *
 * Purpose: Calls Scanning, and becomes the animal that is returned
 *
 * Possible future improvements: make sure the parents are the same species you were before you died
 */

public class IsDead extends AppCompatActivity {
    boolean klar = false;
    Animals djur = new Animals();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_is_dead);
    }

    public void scan(View view) {
        Intent intent = new Intent(this, Scanning.class);
        startActivityForResult(intent, 5);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 5) {
                klar = true;
                djur = data.getParcelableExtra("result");

                Intent returnIntent = new Intent();
                returnIntent.putExtra("animal", djur);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        }
    }
}
