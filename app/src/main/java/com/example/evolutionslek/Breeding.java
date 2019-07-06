package com.example.evolutionslek;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.Random;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Breeding extends AppCompatActivity {
    Animals animal;
    boolean klar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeding);

        Intent intent = getIntent();
        animal = intent.getParcelableExtra("djur");
        ImageView imageView = (ImageView) findViewById(R.id.imageView2);
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        String text = (Integer.toString(animal.mass) + "," + Integer.toString(animal.horns) + "," + Integer.toString(animal.speed) + "," + Integer.toString(animal.defense) + "," + Integer.toString(animal.maxHealth) + "," + Integer.toString(animal.claws) + "," + Integer.toString(animal.attack) + "," + Boolean.toString(animal.herbivore));

        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    public void scan(View view) {
        Intent intent = new Intent(this, Scanning.class);
        intent.putExtra("djur", animal);
        startActivityForResult(intent, 5);
    }

    public void cancelled(View view) {
        Intent intent = new Intent();
        intent.putExtra("klar", false);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    public void end(View view) {
        if (klar == true) {
            finish();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 5) {
                klar = true;
                Toast.makeText(getApplicationContext(), Boolean.toString(klar), Toast.LENGTH_SHORT).show();
                Animals djur = data.getParcelableExtra("result");
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", djur);
                setResult(Activity.RESULT_OK,returnIntent);

            }
        }
    }
}