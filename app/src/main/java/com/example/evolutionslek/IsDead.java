package com.example.evolutionslek;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

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
                Toast.makeText(getApplicationContext(), Boolean.toString(klar), Toast.LENGTH_SHORT).show();
                djur = data.getParcelableExtra("result");
                String text = (Integer.toString(djur.mass) + "," + Integer.toString(djur.horns) + "," + Integer.toString(djur.speed) + "," + Integer.toString(djur.defense) + "," + Integer.toString(djur.maxHealth) + "," + Integer.toString(djur.claws) + "," + Integer.toString(djur.attack) + "," + Boolean.toString(djur.herbivore));
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();

                Intent returnIntent = new Intent();
                returnIntent.putExtra("animal", djur);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        }
    }
}
