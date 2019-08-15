package com.example.evolutionslek;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/*
 * Called by: StartScreen
 *
 * Purpose: The class that everything comes back to, calls EndOfTurn, Eating, ShowQR and IsDead
 *
 * Possible future improvements: Use listview instead of 10 textviews for stats
 */

public class Ingame extends AppCompatActivity {

    public static String ANIMAL = "djur";
    Animals djur = new Animals();
    String lastPlant = "0";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingame);

        Intent intent = getIntent();
        Animals animal = intent.getParcelableExtra(StartScreen.ANIMAL);
        djur = animal;

        updateTraits();

    }

    public void showQR(View view) {
        Intent intent = new Intent(this, ShowQR.class);
        intent.putExtra(ANIMAL, djur);
        startActivity(intent);
    }

    public void breed(View view) { //next turn
        Intent intent = new Intent(this, EndOfTurn.class);
        intent.putExtra(ANIMAL, djur);
        startActivityForResult(intent, 1);
    }

    public void eat(View view) {
            Intent intent = new Intent(this, Eating.class);
            intent.putExtra(ANIMAL, djur);
            intent.putExtra("plant", lastPlant);
            startActivityForResult(intent, 2);
    }

    public void die() {
        die2();
    }
    public void die(View view) {
        final boolean[] c = {false};
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Die");
        alertDialogBuilder.setMessage("Are you sure?");
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                die2();
            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void die2() {
        djur = new Dead();
        Intent intent = new Intent(this, IsDead.class);
        startActivityForResult(intent, 3);
    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            switch(requestCode) {
                case 1:
                    switch (data.getStringExtra("result")) {
                        case "bad":
                            die();
                            break;
                        case "breed":
                            djur = data.getParcelableExtra("animal");
                            break;
                        default:
                            djur = data.getParcelableExtra("animal");
                            break;
                    }
                    break;

                case 2:
                    djur.food += Double.parseDouble(data.getStringExtra("result"))*Math.pow(Math.cbrt(djur.mass), 2)/10;
                    lastPlant = data.getStringExtra("plant");
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
        TextView tv = findViewById(R.id.textView);
        tv1.setText("Mass: " +Long.toString(Math.round(djur.mass)));
        tv2.setText("Horns: " +Long.toString(Math.round(djur.horns)));
        tv3.setText("Speed: " +Long.toString(Math.round(djur.speed)));
        tv4.setText("Defense: " +Long.toString(Math.round(djur.defense)));
        tv5.setText("Max health: " +Long.toString(Math.round(djur.maxHealth)));
        tv6.setText("Health: " +Long.toString(Math.round(djur.health)));
        tv7.setText("Claws: " +Long.toString(Math.round(djur.claws)));
        tv8.setText("Attack: " +Long.toString(Math.round(djur.attack)));
        tv9.setText("Food: " +Long.toString(Math.round(djur.food)));
        tv.setText("Victory points:" + Integer.toString(djur.winpoints));
        if(djur.herbivore == true){
            tv10.setText("Herbivore");
        }
        else{
            tv10.setText("Carnivore");
        }
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", djur);
        setResult(Activity.RESULT_OK, returnIntent);
    }
}