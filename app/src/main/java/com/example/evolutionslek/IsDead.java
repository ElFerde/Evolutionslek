package com.example.evolutionslek;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Random;
import java.lang.Math;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class IsDead extends AppCompatActivity {
    private ZXingScannerView zXingScannerView;
    byte scannedParents = 0;
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
                /*

                Animals parent1;
                Animals parent2;
                Animals djur = new Animals();
                if(scannedParents==0) {
                    parent1 = data.getParcelableExtra("result");
                    scannedParents = 1;
                    Intent intent = new Intent(this, Scanning.class);
                    startActivityForResult(intent, 5);
                }
                else if(scannedParents==1){
                    parent2 = data.getParcelableExtra("result");

                    Random r = new Random();
                    djur.mass = (int) Math.round(r.nextGaussian()*10+(parent1.mass+parent2.mass)/2);
                    djur.horns =(int) Math.round(r.nextGaussian()*10+(parent1.horns+parent2.horns)/2);
                    djur.speed =(int) Math.round(r.nextGaussian()*10+(parent1.speed+parent2.speed)/2);
                    djur.defense=(int) Math.round(r.nextGaussian()*10+(parent1.defense+parent2.defense)/2);
                    djur.maxHealth =(int) Math.round(r.nextGaussian()*10+(parent1.maxHealth+parent2.maxHealth)/2);
                    djur.claws =(int) Math.round(r.nextGaussian()*10+(parent1.claws+parent2.claws)/2);
                    djur.attack =(int) Math.round(r.nextGaussian()*10+(parent1.attack+parent2.attack)/2);
                    djur.herbivore = parent1.herbivore;

                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("result", djur);
                    setResult(Activity.RESULT_OK,returnIntent);
                }*/
            }
        }
    }

    /*public void scan(View view) {
        zXingScannerView = new ZXingScannerView(getApplicationContext());
        setContentView(zXingScannerView);
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
    }
    @Override
    public void handleResult(Result result) {
        String data = result.getText();
        String[] data2 = data.split(",");
        Animals djur = new Animals();
        djur.mass = Integer.parseInt(data2[0]);
        djur.horns = Integer.parseInt(data2[1]);
        djur.speed = Integer.parseInt(data2[2]);
        djur.defense= Integer.parseInt(data2[3]);
        djur.maxHealth = Integer.parseInt(data2[4]);
        djur.claws = Integer.parseInt(data2[5]);
        djur.attack = Integer.parseInt(data2[6]);
        djur.herbivore = Boolean.parseBoolean(data2[7]);
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", djur);
        finish();
    }*/
}
