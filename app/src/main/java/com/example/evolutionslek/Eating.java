package com.example.evolutionslek;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static com.example.evolutionslek.Ingame.ANIMAL;

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
            if(data2[1].equals(lastPlant)){
                finish();
            }
            else{
                lastPlant = data2[1];
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",data2[0]);
                returnIntent.putExtra("plant",lastPlant);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        }
        else{
            double a = Math.random();
            boolean success = true;
            if(true)/*a/(1-a)<djur.speed/Double.parseDouble(data2[2]))*/{
                while(Double.parseDouble(data2[4])>0) {
                    double b = Math.random();
                    if (b / 2 > djur.health / djur.maxHealth) {
                        success = false;
                        break;
                    } else{
                        data2[4] = Double.toString(Double.parseDouble(data2[4]) - djur.attack/Double.parseDouble(data2[3])*b*5 );
                        djur.health -= Double.parseDouble(data2[3])*b/djur.attack;
                        Toast.makeText(getApplicationContext(), "Hejhej", Toast.LENGTH_SHORT).show();

                    }
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
    public void finish(View view){
        finish();
    }
}