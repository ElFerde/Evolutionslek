package com.example.evolutionslek;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static com.example.evolutionslek.Ingame.ANIMAL;

/*
 * Called by: Ingame
 *
 * Purpose: Reads a qr-code, checks if it is possible to eat, and eats if possible
 *
 * Possible future improvements: dealing damage to half-eaten prey, the lastPlant-system?
 */

public class Eating extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView zXingScannerView;
    Animals djur;
    String lastPlant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        djur = intent.getParcelableExtra(ANIMAL);
        lastPlant = intent.getStringExtra("plant");
        zXingScannerView = new ZXingScannerView(getApplicationContext());
        setContentView(zXingScannerView);
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
    }

    @Override
    protected void onPause(){
        super.onPause();
        zXingScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result){
        String data = result.getText();

        String[]data2 = data.split(",");
        Toast.makeText(getApplicationContext(), data2[0], Toast.LENGTH_SHORT).show();

        if(djur.herbivore){
            if(data2.length == 2){
                if(data2[1].equals(lastPlant)){
                    finish();
                }
                else {
                    lastPlant = data2[1];
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("result", data2[0]);
                    returnIntent.putExtra("plant", lastPlant);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }
            }
            finish();
        }
        else{
            double r = Math.random();
            if(data2.length < 8 && 1.5*r/(1-r)-0.5 > djur.speed/Integer.parseInt(data2[2])){

                boolean success ;
                if(Integer.parseInt(data2[1]) == 0 || Integer.parseInt(data2[3])  == 0){
                    success = true;
                }
                double d = djur.mass/Integer.parseInt(data2[0]);
                double a = djur.claws/Integer.parseInt(data2[3])*d;
                double b = djur.attack/Integer.parseInt(data2[2])*d;
                double c = Math.random()*(a+b);
                if (c > djur.health/Integer.parseInt(data2[4])){
                    success = true;
                }
                else{
                    success = false;
                }
                if (success) {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("result", data2[0]);
                    returnIntent.putExtra("health", Double.toString(djur.health));
                    returnIntent.putExtra("health2", data2[4]);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }
            }
            finish();
        }
    }
}