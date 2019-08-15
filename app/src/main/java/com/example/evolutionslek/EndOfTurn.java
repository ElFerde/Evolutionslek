package com.example.evolutionslek;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

/*
 * Called by: Ingame
 *
 * Purpose: Checks if you die, evolve, are able to breed, get winpoint/-s
 *
 * Possible future improvements: edit layout to display stats when breeding, change name of minEvolution, it decides winpoints not evolution
 */

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
        int n = 0;
        while(djur.food >= Math.pow(minEvolution, n+1)*djur.mass){
            Toast.makeText(this, "Hej", Toast.LENGTH_SHORT);
            n ++;
        }
        if(n != 0){
            djur.food += djur.mass;
            djur.winpoints += n;
            djur.food -= Math.pow(minEvolution, n)*djur.mass;
        }
        if(djur.food >= minBreeding*djur.mass) {
            showBreeding();

            Intent returnIntent = new Intent();
            returnIntent.putExtra("result", "breed");
            returnIntent.putExtra("animal", djur);
            klar = true;
            setResult(Activity.RESULT_OK, returnIntent);
            djur.food -= djur.mass*minBreeding;
        }
        else if(djur.food >= minFood*djur.mass) {
            Intent intent = new Intent(this, Evolution.class);
            djur.food -= djur.mass*minFood;
            intent.putExtra("animal", djur);
            startActivityForResult(intent, 9);

        }
        else {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result", "bad");
            klar = true;
            setResult(Activity.RESULT_OK, returnIntent);
        }
    }

    public void showBreeding(){
        ImageView imageView = findViewById(R.id.imageView3);
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        String text = (Double.toString(djur.mass) + "," + Double.toString(djur.horns) + "," + Double.toString(djur.speed) + "," + Double.toString(djur.defense) + "," + Double.toString(djur.maxHealth) + "," + Double.toString(djur.claws) + "," + Double.toString(djur.attack) + "," + Boolean.toString(djur.herbivore) + "," + djur.species + "," +Integer.toString(djur.winpoints));

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
            if(requestCode == 9) {
                djur = data.getParcelableExtra("animal");
                showBreeding();
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", "good");
                returnIntent.putExtra("animal", djur);
                klar = true;
                setResult(Activity.RESULT_OK, returnIntent);
            }
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