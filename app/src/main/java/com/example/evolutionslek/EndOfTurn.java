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

    public double randomEvolution(double a){
        int s = ((int) (Math.random()*2) * 2)  - 1;
        return(a + s + 0.01*s*a);

    }

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
            int a = 0;
            while(a<2) {
                int r = (int) (Math.random() * 7) + 1;
                switch(r){
                    case 1:
                        djur.mass = Math.abs(randomEvolution(djur.mass));
                        break;
                    case 2:
                        djur.speed = Math.abs(randomEvolution(djur.speed));
                        break;
                    case 3:
                        djur.horns = Math.abs(randomEvolution(djur.horns));
                        break;
                    case 4:
                        djur.maxHealth = Math.abs(randomEvolution(djur.maxHealth));
                        break;
                    case 5:
                        djur.claws = Math.abs(randomEvolution(djur.claws));
                        break;
                    case 6:
                        djur.attack = Math.abs(randomEvolution(djur.attack));
                        break;
                    case 7:
                        djur.defense = Math.abs(randomEvolution(djur.defense));
                        break;
                }
                a++;
            }
            returnIntent.putExtra("animal", djur);
        }
        else {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result", "bad");
            klar = true;
            setResult(Activity.RESULT_OK, returnIntent);
            //Toast.makeText(getApplicationContext(), "Hej", Toast.LENGTH_SHORT).show();
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
            if(requestCode == 9) {
                djur = data.getParcelableExtra("animal");
                showBreeding();
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", "evolution");
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