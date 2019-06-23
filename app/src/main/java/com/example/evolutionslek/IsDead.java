package com.example.evolutionslek;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class IsDead extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView zXingScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_is_dead);
    }
    public void scan(View view) {
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
    }
}
