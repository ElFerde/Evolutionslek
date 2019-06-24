package com.example.evolutionslek;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.Result;

import java.util.Random;
import java.lang.Math;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Scanning extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView zXingScannerView;
    Animals animal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanning);
        scan();
    }

    @Override
    public void handleResult(Result result){
        String data = result.getText(); // gets content of qr code
        Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show(); // convert to string
        String[] data2 = data.split(","); // split to array of strings
        //Animals nyttDjur = new Animals(); //create new animal

        Animals scannedAnimal = new Animals();
        scannedAnimal.mass = Integer.parseInt(data2[0]); //set variables a to h to stats of the old animal
        scannedAnimal.horns = Integer.parseInt(data2[1]);
        scannedAnimal.speed = Integer.parseInt(data2[2]);
        scannedAnimal.defense = Integer.parseInt(data2[3]);
        scannedAnimal.maxHealth = Integer.parseInt(data2[4]);
        scannedAnimal.claws = Integer.parseInt(data2[5]);
        scannedAnimal.attack = Integer.parseInt(data2[6]);
        scannedAnimal.herbivore = Boolean.parseBoolean(data2[7]);

        Intent intent = getIntent();
        animal = intent.getParcelableExtra("djur"); // fetches the old animal
        Random r = new Random();

        //sets stats of the new animal, based on stats of the old animal and the scanned animal
        //nyttDjur.mass = (int) Math.round(r.nextGaussian()*10+(a+animal.mass)/2);
        //nyttDjur.horns =(int) Math.round(r.nextGaussian()*10+(b+animal.horns)/2);
        //nyttDjur.speed =(int) Math.round(r.nextGaussian()*10+(c+animal.speed)/2);
        //nyttDjur.defense=(int) Math.round(r.nextGaussian()*10+(d+animal.defense)/2);
        //nyttDjur.maxHealth =(int) Math.round(r.nextGaussian()*10+(e+animal.maxHealth)/2);
        //nyttDjur.claws =(int) Math.round(r.nextGaussian()*10+(f+animal.claws)/2);
        //nyttDjur.attack =(int) Math.round(r.nextGaussian()*10+(g+animal.attack)/2);
        //nyttDjur.herbivore = h;

        Toast.makeText(getApplicationContext(), Integer.toString(animal.food), Toast.LENGTH_SHORT).show();
        //nyttDjur.food = animal.food; //sets food of the new animal to food of the old animal

        //Toast.makeText(getApplicationContext(), djur.food, Toast.LENGTH_SHORT).show();

        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", scannedAnimal);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
    @Override
    protected void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();
    }
    public void scan(){
        zXingScannerView = new ZXingScannerView(getApplicationContext());
        setContentView(zXingScannerView);
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
    }
}
