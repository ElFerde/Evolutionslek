package com.example.evolutionslek;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

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
        String text = (Double.toString(animal.mass) + "," + Double.toString(animal.horns) + "," + Double.toString(animal.speed) + "," + Double.toString(animal.defense) + "," + Double.toString(animal.maxHealth) + "," + Double.toString(animal.claws) + "," + Double.toString(animal.attack) + "," + Boolean.toString(animal.herbivore));

        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    public void end(View view) {
        finish();
    }
}