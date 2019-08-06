package com.example.evolutionslek;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Evolution extends AppCompatActivity {
    Animals djur;
    ListView listView;
    String[] egenskaper = new String[8];

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

        egenskaper[0] = Double.toString(djur.mass);
        egenskaper[1] = Double.toString(djur.horns);
        egenskaper[2] = Double.toString(djur.speed);
        egenskaper[3] = Double.toString(djur.defense);
        egenskaper[4] = Double.toString(djur.maxHealth);
        egenskaper[5] = Double.toString(djur.claws);
        egenskaper[6] = Double.toString(djur.attack);
        egenskaper[7] = Double.toString(djur.food);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                egenskaper );
        //listView.setAdapter(arrayAdapter);
    }

}
