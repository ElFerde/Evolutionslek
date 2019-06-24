package com.example.evolutionslek;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class Ingame extends AppCompatActivity {

    public static String ANIMAL = "djur";
    Animals djur = new Animals();

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
        Intent intent = new Intent(this, Eating.class);
        intent.putExtra(ANIMAL, djur);
        startActivityForResult(intent, 2);
    }

    public void die(View view) {
        Intent intent = new Intent(this, IsDead.class);
        startActivityForResult(intent, 3);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            if(requestCode == 1){
                djur = data.getParcelableExtra("result");

            }

            if(requestCode == 2){
                djur.food += Integer.parseInt(data.getStringExtra("result"));
            }

            if(requestCode == 3){
                djur = data.getParcelableExtra("result");
            }
        }
        updateTraits();
    }
    void updateTraits(){
        TextView tv1 = (TextView)findViewById(R.id.textView2);
        TextView tv2 = (TextView)findViewById(R.id.textView3);
        TextView tv3 = (TextView)findViewById(R.id.textView4);
        TextView tv4 = (TextView)findViewById(R.id.textView5);
        TextView tv5 = (TextView)findViewById(R.id.textView6);
        TextView tv6 = (TextView)findViewById(R.id.textView7);
        TextView tv7 = (TextView)findViewById(R.id.textView8);
        TextView tv8 = (TextView)findViewById(R.id.textView10);
        TextView tv9 = (TextView)findViewById(R.id.textView11);
        TextView tv10 = (TextView)findViewById(R.id.textView12);
        tv1.setText("Mass: " +Integer.toString(djur.mass));
        tv2.setText("Horns: " +Integer.toString(djur.horns));
        tv3.setText("Speed: " +Integer.toString(djur.speed));
        tv4.setText("Defense: " +Integer.toString(djur.defense));
        tv5.setText("Max health: " +Integer.toString(djur.maxHealth));
        tv6.setText("Health: " +Integer.toString(djur.health));
        tv7.setText("Claws: " +Integer.toString(djur.claws));
        tv8.setText("Attack: " +Integer.toString(djur.attack));
        tv9.setText("Food: " +Integer.toString(djur.food));
        if(djur.herbivore == true){
            tv10.setText("Herbivore");
        }
        else{
            tv10.setText("Carnivore");
        }
    }
}