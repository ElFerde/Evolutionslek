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
        setContentView(R.layout.activity_eating);
        Intent intent = getIntent();
        djur = intent.getParcelableExtra(ANIMAL);
        lastPlant = intent.getStringExtra("plant");
    }
        public void scan(View view){
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
            }
        }
        else{

        }

        //add function of stats
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",data2[0]);
        returnIntent.putExtra("plant",lastPlant);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();

    }
    public void finish(View view){
        finish();
    }
}