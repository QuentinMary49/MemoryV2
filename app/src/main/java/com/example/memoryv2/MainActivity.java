package com.example.memoryv2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;
    private ImageView image5;
    private ImageView image6;

    private ImageView carte_prec = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        image5 = findViewById(R.id.image5);
        image6 = findViewById(R.id.image6);
    }

    @Override
    protected void onResume() {
        super.onResume();
        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
        image3.setOnClickListener(this);
        image4.setOnClickListener(this);
        image5.setOnClickListener(this);
        image6.setOnClickListener(this);


    }
    /*public boolean est_retourne(){

    }*/

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.image1){
            ImageView imageVue = v.findViewById(id);
            imageVue.setImageResource(R.drawable.fraise);
        }
        else if (id == R.id.image2){
            ImageView imageVue = v.findViewById(id);
            imageVue.setImageResource(R.drawable.peche);
        }
        else if (id == R.id.image3){
            ImageView imageVue = v.findViewById(id);
            imageVue.setImageResource(R.drawable.pasteque);
        }
        else if (id == R.id.image4){
            ImageView imageVue = v.findViewById(id);
            imageVue.setImageResource(R.drawable.peche);
        }
        else if (id == R.id.image5){
            ImageView imageVue = v.findViewById(id);
            imageVue.setImageResource(R.drawable.fraise);
        }
        else if (id == R.id.image6){
            ImageView imageVue = v.findViewById(id);
            imageVue.setImageResource(R.drawable.pasteque);
        }
        if(carte_prec == null) {
            carte_prec = v.findViewById(id);
        }
        else{
            ImageView img1 = v.findViewById(id);
            if(img1 != carte_prec){
                Bitmap bimg1 = ((BitmapDrawable) img1.getDrawable()).getBitmap();
                Bitmap bimg2 = ((BitmapDrawable) carte_prec.getDrawable()).getBitmap();
                if(bimg1 == bimg2){
                    Toast.makeText(MainActivity.this, "C'est gagné", Toast.LENGTH_LONG).show();
                    carte_prec = null;
                }
                else{
                    img1.setImageResource(R.drawable.backcard);
                    carte_prec.setImageResource(R.drawable.backcard);
                    carte_prec = null;
                }
            }else{
                img1.setImageResource(R.drawable.backcard);
                carte_prec.setImageResource(R.drawable.backcard);
                carte_prec = null;
            }

        }
    }
}