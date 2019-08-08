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
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class EndOfTurn extends AppCompatActivity {
    Animals djur;
    Boolean klar;
    public static double minFood = 0.5;
    public static double minBreeding = 1;
    public static double minEvolution = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_of_turn);
        Intent i = getIntent();
        djur = i.getParcelableExtra(Ingame.ANIMAL);
        if(djur.food >= minEvolution*djur.mass){
            Intent intent = new Intent(this, Evolution.class);
            intent.putExtra("animal", djur);
            startActivityForResult(intent, 9);
        }
        else if(djur.food >= minBreeding*djur.mass) {
            showBreeding();

            Intent returnIntent = new Intent();
            returnIntent.putExtra("result", "breed");
            klar = true;
            setResult(Activity.RESULT_OK, returnIntent);
        }
        else if(djur.food >= minFood*djur.mass) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result", "good");
            klar = true;
            setResult(Activity.RESULT_OK, returnIntent);
        }
        else {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result", "bad");
            klar = true;
            setResult(Activity.RESULT_OK, returnIntent);
            Toast.makeText(getApplicationContext(), "Hej", Toast.LENGTH_SHORT).show();
        }
    }

    public void showBreeding(){
        ImageView imageView = findViewById(R.id.imageView3);
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        String text = (Double.toString(djur.mass) + "," + Double.toString(djur.horns) + "," + Double.toString(djur.speed) + "," + Double.toString(djur.defense) + "," + Double.toString(djur.maxHealth) + "," + Double.toString(djur.claws) + "," + Double.toString(djur.attack) + "," + Boolean.toString(djur.herbivore) + "," + djur.species);

        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK) {
            Intent i = getIntent();
            djur = i.getParcelableExtra("animal");

            showBreeding();

            Intent returnIntent = new Intent();
            returnIntent.putExtra("result", "evolution");
            returnIntent.putExtra("animal", djur);
            klar = true;
            setResult(Activity.RESULT_OK, returnIntent);
        }
    }

    public void nextTurn(View view) {
        finish();
    }
    public void end(View view) {
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();
    }
}