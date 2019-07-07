package com.example.evolutionslek;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import static com.example.evolutionslek.Ingame.ANIMAL;

public class EndOfTurn extends AppCompatActivity {
    Animals djur;
    Boolean klar;
    public static int minFood = 10;
    public static int minBreeding = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_of_turn);
        Intent i = getIntent();
        djur = i.getParcelableExtra(Ingame.ANIMAL);
        if(djur.food >= minBreeding) {
            Intent intent = new Intent(this, Breeding.class);
            intent.putExtra("djur", djur);
            startActivityForResult(intent, 4);
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result", "breed");
            klar = true;
            setResult(Activity.RESULT_OK, returnIntent);
        }
        else if(djur.food >= minFood) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result", "good");
            klar = true;
            setResult(Activity.RESULT_OK, returnIntent);
        }
        else {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result", "bad");
            klar = true;
            setResult(Activity.RESULT_OK, returnIntent);
            Toast.makeText(getApplicationContext(), "Hej", Toast.LENGTH_SHORT).show();
        }
    }

    public void breed(View view) {

        Intent intent = new Intent(this, Breeding.class);
        intent.putExtra("djur", djur);
        startActivityForResult(intent, 4);
    }

    public void nextTurn(View view) {
        finish();
    }
}