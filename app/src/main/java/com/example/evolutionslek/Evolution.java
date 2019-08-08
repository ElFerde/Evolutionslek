package com.example.evolutionslek;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Evolution extends AppCompatActivity {
    Animals djur;
    ListView listView;
    double[] egenskaper = new double[8];
    String[] text = new String[8];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evolution);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        djur = intent.getParcelableExtra("animal");

        listView = findViewById(R.id.dynamic);
        reset();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (egenskaper[7] >= 100 && position != 7) {
                    egenskaper[position] = egenskaper[position] * 1.05 + 1;
                    egenskaper[7] -= 100;
                }
                updateList();
            }
        });
    }

    public void updateList() {
        text[0] = "Mass: " + Double.toString(egenskaper[0]);
        text[1] = "Horns: " + Double.toString(egenskaper[1]);
        text[2] = "Speed: " + Double.toString(egenskaper[2]);
        text[3] = "Defense: " + Double.toString(egenskaper[3]);
        text[4] = "Max Health: " + Double.toString(egenskaper[4]);
        text[5] = "Claws: " + Double.toString(egenskaper[5]);
        text[6] = "Attack: " + Double.toString(egenskaper[6]);
        text[7] = "Food: " + Double.toString(egenskaper[7]);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                text);
        listView.setAdapter(arrayAdapter);
    }

    public void finished(View view) {
        djur.mass = egenskaper[0];
        djur.horns = egenskaper[1];
        djur.speed = egenskaper[2];
        djur.defense = egenskaper[3];
        djur.maxHealth = egenskaper[4];
        djur.claws = egenskaper[5];
        djur.attack = egenskaper[6];
        djur.food = egenskaper[7];

        Toast.makeText(getApplicationContext(), Double.toString(egenskaper[1]), Toast.LENGTH_SHORT).show();

        Intent returnIntent = new Intent();
        returnIntent.putExtra("animal", djur);
        setResult(RESULT_OK, returnIntent);
        finish();
    }

    public void reset() {
        egenskaper[0] = djur.mass;
        egenskaper[1] = djur.horns;
        egenskaper[2] = djur.speed;
        egenskaper[3] = djur.defense;
        egenskaper[4] = djur.maxHealth;
        egenskaper[5] = djur.claws;
        egenskaper[6] = djur.attack;
        egenskaper[7] = djur.food;
        updateList();
    }


    public void reset(View view){
        reset();
    }
}