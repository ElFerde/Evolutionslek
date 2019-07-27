package com.example.evolutionslek;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.Math;

public class Ingame extends AppCompatActivity {

    public static String ANIMAL = "djur";
    Animals djur = new Animals();
    int lastPlant;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingame);

        Intent intent = getIntent();
        String animal = intent.getStringExtra(StartScreen.ANIMAL);
        switch (animal) {
            case "1":
                djur = new Husmus();
                break;
            case "2":
                djur = new Unicorn();
                break;
            case "3":
                djur = new Pedro();
                break;
            case "4":
                djur = new Gruffalo();
                break;
        }
        updateTraits();

    }

    public void showQR(View view) {
        Intent intent = new Intent(this, ShowQR.class);
        intent.putExtra(ANIMAL, djur);
        startActivity(intent);
    }

    public void breed(View view) {
        Intent intent = new Intent(this, EndOfTurn.class);
        intent.putExtra(ANIMAL, djur);
        startActivityForResult(intent, 1);
    }

    public void eat(View view) {
        if(!djur.herbivore) {
            Intent intent = new Intent(this, Eating.class);
            intent.putExtra(ANIMAL, djur);
            startActivityForResult(intent, 2);
        }
        else{
            Intent intent = new Intent(this, EatPlant.class);
            intent.putExtra(ANIMAL, djur);
            intent.putExtra("plant", lastPlant);
            startActivityForResult(intent, 12);
        }
    }

    public void die(View view) {
        Intent intent = new Intent(this, IsDead.class);
        startActivityForResult(intent, 3);
    }
    public void die() {
        Intent intent = new Intent(this, IsDead.class);
        startActivityForResult(intent, 3);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            switch(requestCode) {
                case 1:
                    Toast.makeText(getApplicationContext(), data.getStringExtra("result"), Toast.LENGTH_SHORT).show();
                    if (data.getStringExtra("result").equals("bad")) {
                        die();
                    } else if (data.getStringExtra("result").equals("breed")) {
                        djur.food -= EndOfTurn.minBreeding;
                    } else {
                        djur.food -= EndOfTurn.minFood;
                    }
                    break;

                case 2:
                    djur.food += Integer.parseInt(data.getStringExtra("result"));
                    break;

                case 12:
                    djur.food += Integer.parseInt(data.getStringExtra("result"));
                    lastPlant = Integer.parseInt(data.getStringExtra("plant"));
                    break;

                case 3:
                    djur = data.getParcelableExtra("animal");
                    break;
            }
        }
        updateTraits();
    }
    void updateTraits(){
        TextView tv1 = findViewById(R.id.textView2);
        TextView tv2 = findViewById(R.id.textView3);
        TextView tv3 = findViewById(R.id.textView4);
        TextView tv4 = findViewById(R.id.textView5);
        TextView tv5 = findViewById(R.id.textView6);
        TextView tv6 = findViewById(R.id.textView7);
        TextView tv7 = findViewById(R.id.textView8);
        TextView tv8 = findViewById(R.id.textView10);
        TextView tv9 = findViewById(R.id.textView11);
        TextView tv10 =findViewById(R.id.textView12);
        tv1.setText("Mass: " +Long.toString(Math.round(djur.mass)));
        tv2.setText("Horns: " +Long.toString(Math.round(djur.horns)));
        tv3.setText("Speed: " +Long.toString(Math.round(djur.speed)));
        tv4.setText("Defense: " +Long.toString(Math.round(djur.defense)));
        tv5.setText("Max health: " +Long.toString(Math.round(djur.maxHealth)));
        tv6.setText("Health: " +Long.toString(Math.round(djur.health)));
        tv7.setText("Claws: " +Long.toString(Math.round(djur.claws)));
        tv8.setText("Attack: " +Long.toString(Math.round(djur.attack)));
        tv9.setText("Food: " +Long.toString(Math.round(djur.food)));
        if(djur.herbivore == true){
            tv10.setText("Herbivore");
        }
        else{
            tv10.setText("Carnivore");
        }
    }
}