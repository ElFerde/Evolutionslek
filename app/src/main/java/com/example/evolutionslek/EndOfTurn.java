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
    Animals animal;
    Boolean klar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_of_turn);
        Intent i = getIntent();
        djur = i.getParcelableExtra(Ingame.ANIMAL);
    }

    public void breed(View view) {

        Intent intent = new Intent(this, Breeding.class);
        intent.putExtra("djur", djur);
        startActivityForResult(intent, 4);
    }

    public void nextTurn(View view) {
        if(djur.food > djur.mass) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result", animal);
            klar = true;
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
        else{
            Toast.makeText(getApplicationContext(), "not enough food", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelled(View view) {
        klar = false;
        finish();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 4 && resultCode == RESULT_OK){
             animal = data.getParcelableExtra("result");
            Toast.makeText(getApplicationContext(), "Hejjj", Toast.LENGTH_SHORT).show();
        }

    }
}