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
    Animals parent = new Animals();
    Animals djur = new Animals();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanning);
        parent.maxHealth=0;
        scan();
    }

    @Override
    public void handleResult(Result result){
        String data = result.getText();
        Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();
        String[] data2 = data.split(",");
        double a = Integer.parseInt(data2[0]);
        double b = Integer.parseInt(data2[1]);
        double c = Integer.parseInt(data2[2]);
        double d = Integer.parseInt(data2[3]);
        double e = Integer.parseInt(data2[4]);
        double f = Integer.parseInt(data2[5]);
        double g = Integer.parseInt(data2[6]);
        boolean h = Boolean.parseBoolean(data2[7]);


        //Toast.makeText(getApplicationContext(), Integer.toString(animal.food), Toast.LENGTH_SHORT).show();
        //djur.food = animal.food;
        //Toast.makeText(getApplicationContext(), djur.food, Toast.LENGTH_SHORT).show();

        if(parent.maxHealth==0){ //
            parent.mass = (int) a;
            parent.horns = (int) b;
            parent.speed = (int) c;
            parent.defense = (int) d;
            parent.maxHealth = (int) e;
            parent.claws = (int) f;
            parent.attack = (int) g;
            parent.herbivore = h;

            scan();
        }
        else{
            Random r = new Random();
            djur.mass = (int) Math.round(r.nextGaussian()*10+(a+parent.mass)/2);
            djur.horns =(int) Math.round(r.nextGaussian()*10+(b+parent.horns)/2);
            djur.speed =(int) Math.round(r.nextGaussian()*10+(c+parent.speed)/2);
            djur.defense=(int) Math.round(r.nextGaussian()*10+(d+parent.defense)/2);
            djur.maxHealth =(int) Math.round(r.nextGaussian()*10+(e+parent.maxHealth)/2);
            djur.claws =(int) Math.round(r.nextGaussian()*10+(f+parent.claws)/2);
            djur.attack =(int) Math.round(r.nextGaussian()*10+(g+parent.attack)/2);

            Intent returnIntent = new Intent();
            returnIntent.putExtra("result", djur);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
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
