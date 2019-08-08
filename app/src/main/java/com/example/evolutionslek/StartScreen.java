package com.example.evolutionslek;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartScreen extends AppCompatActivity {
    public static String ANIMAL = "animal";
    Animals djur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
    }

    public void createAnimal1(View view) {
        sendAnimal(new Husmus());
    }

    public void createAnimal2(View view) {
        sendAnimal(new Unicorn());
    }

    public void createAnimal3(View view) {
        sendAnimal(new Pedro());
    }

    public void createAnimal4(View view) {
        sendAnimal(new Gruffalo());
    }

    public void hideButtons() {
        Button button1 = (Button) findViewById(R.id.button);
        button1.setVisibility(View.INVISIBLE);
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setVisibility(View.INVISIBLE);
        Button button3 = (Button) findViewById(R.id.button3);
        button3.setVisibility(View.INVISIBLE);
        Button button4 = (Button) findViewById(R.id.button4);
        button4.setVisibility(View.INVISIBLE);
        Button button7 = (Button) findViewById(R.id.button7);
        button7.setVisibility(View.VISIBLE);
    }

    public void continu(View view) {
        sendAnimal(djur);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            djur = data.getParcelableExtra("result");

        }
    }
    public void sendAnimal(Animals animal){
        Intent intent = new Intent(this, Ingame.class);
        intent.putExtra(ANIMAL, animal);
        hideButtons();
        startActivityForResult(intent, 21);
    }
}