package com.example.evolutionslek;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class ShowQR extends AppCompatActivity  {
    double[] animal;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_qr);
        Intent intent = getIntent();
        Animals djur = intent.getExtras().getParcelable(Ingame.ANIMAL);
        animal = new double[]{djur.mass, djur.horns, djur.speed, djur.defense, djur.maxHealth, djur.health, djur.claws, djur.attack, djur.food};
        String text = new String();

        String code = intent.getStringExtra("code");

        switch (code){
            case "eaten":
                //mass, horns, speed, defense, health, claws
                text = JoinDoubles(new int[] {0, 1, 2, 3, 5, 6});
                break;
            case "eating":
                text = Double.toString(intent.getDoubleExtra("damage", 1));
                break;
        }

        generateQR(text);
    }
    private void generateQR(String text){
        ImageView imageView = findViewById(R.id.imageView);
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,200,200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    String JoinDoubles(int[] indexes){
        String result = new String();
        for (int x=0; x<indexes.length; x++){
            result = result + Double.toString(animal[indexes[x]]);
            if(x!=indexes.length-1){
                result = result + ",";
            }
        }
        return(result);
    }
}