package com.example.evolutionslek;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/*
 * Called by:
 *
 * Purpose:
 *
 * Possible future improvements:
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
                //Toast.makeText(getApplicationContext(), Boolean.toString(klar), Toast.LENGTH_SHORT).show();
                djur = data.getParcelableExtra("result");
                String text = (Double.toString(djur.mass) + "," + Double.toString(djur.horns) + "," + Double.toString(djur.speed) + "," + Double.toString(djur.defense) + "," + Double.toString(djur.maxHealth) + "," + Double.toString(djur.claws) + "," + Double.toString(djur.attack) + "," + Boolean.toString(djur.herbivore) + "," + djur.species);
                //Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();

                Intent returnIntent = new Intent();
                returnIntent.putExtra("animal", djur);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        }
    }
}
