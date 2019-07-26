package com.example.evolutionslek;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.zxing.Result;

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
        int a = Integer.parseInt(data2[0]);
        int b = Integer.parseInt(data2[1]);
        int c = Integer.parseInt(data2[2]);
        int d = Integer.parseInt(data2[3]);
        int e = Integer.parseInt(data2[4]);
        int f = Integer.parseInt(data2[5]);
        int g = Integer.parseInt(data2[6]);
        boolean h = Boolean.parseBoolean(data2[7]);


        if(parent.maxHealth==0){ // parent max health is set to 0 in onCreate()
            parent.mass = a;
            parent.horns = b;
            parent.speed = c;
            parent.defense = d;
            parent.maxHealth = e;
            parent.claws = f;
            parent.attack = g;

            scan();
        }
        else{
            djur.mass = newStats(parent.mass, a);
            djur.horns = newStats(parent.horns, b);
            djur.speed = newStats(parent.speed, b);
            djur.defense= newStats(parent.defense, b);
            djur.maxHealth = newStats(parent.maxHealth, b);
            djur.claws = newStats(parent.claws, b);
            djur.attack = newStats(parent.attack, b);
            djur.herbivore=h;

            Intent returnIntent = new Intent();
            returnIntent.putExtra("result", djur);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
    }
    public int newStats(int stat1, int stat2){
        double random1 = Math.random();
        double random2 = Math.random()/2 + 0.75; //creates random between 0.75 - 1.25, should probably be done in a better way
        double result;
        result = (stat1*random1 + stat2*(1-random1))*random2;
        return (int) Math.round(result);
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
