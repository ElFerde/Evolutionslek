package com.example.evolutionslek;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.zxing.Result;

import java.util.Random;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Breeding extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeding);
    }

    @Override
    public void handleResult(Result result) {
        Intent intent = getIntent();
        Animals animal = intent.getParcelableExtra(Ingame.ANIMAL);
        String data = result.getText();
        String[] data2 = data.split(",");
        Animals djur = new Animals();
        int a = Integer.parseInt(data2[0]);
        int b = Integer.parseInt(data2[1]);
        int c = Integer.parseInt(data2[2]);
        int d = Integer.parseInt(data2[3]);
        int e = Integer.parseInt(data2[4]);
        int f = Integer.parseInt(data2[5]);
        int g = Integer.parseInt(data2[6]);
        boolean h = Boolean.parseBoolean(data2[7]);
        Random r = new Random();
        djur.mass = (int) r.nextGaussian()*(Math.abs(a-animal.mass)+(a+animal.mass)/2);
        djur.horns =(int) r.nextGaussian()*(Math.abs(b-animal.horns)+(b+animal.horns)/2);
        djur.speed =(int) r.nextGaussian()*(Math.abs(c-animal.speed)+(c+animal.speed)/2);
        djur.defense=(int) r.nextGaussian()*(Math.abs(d-animal.defense)+(d+animal.defense)/2);
        djur.maxHealth =(int) r.nextGaussian()*(Math.abs(e-animal.maxHealth)+(e+animal.maxHealth)/2);
        djur.claws =(int) r.nextGaussian()*(Math.abs(f-animal.claws)+(f+animal.claws)/2);
        djur.attack =(int) r.nextGaussian()*(Math.abs(g-animal.attack)+(g+animal.attack)/2);
        djur.herbivore = h;
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", djur);
    }
}
