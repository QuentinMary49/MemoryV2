package com.example.memoryv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;
    private ImageView image5;
    private ImageView image6;
    private List<String> toast_gg = null;
    private Resources resources = null;

    private ImageView carte_prec = null;
    Integer[] tabCartes = {R.drawable.fraise, R.drawable.fraise, R.drawable.peche, R.drawable.peche, R.drawable.pasteque, R.drawable.pasteque};
    private List<Integer> listeCartes = Arrays.asList(tabCartes);

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
        toast_gg = new ArrayList<>(Arrays.asList(resources.getStringArray(R.array.toast_gg)));
        melangerCarte();
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

    private int i=0;
    protected void paireCarte(ImageView img){
        img.setOnClickListener(null);
        carte_prec.setOnClickListener(null);
        Toast.makeText(MainActivity.this,toast_gg.get(i), Toast.LENGTH_LONG).show();
        i += 1;
        carte_prec = null;
    }
    protected void resetCarte(ImageView img){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                img.setImageResource(R.drawable.backcard);
                carte_prec.setImageResource(R.drawable.backcard);
                carte_prec = null;
            }
        }, 1000);
    }
    protected void melangerCarte(){
        Collections.shuffle(listeCartes);
    }
    protected Bitmap convCarteOctet(ImageView img){
        Bitmap b = ((BitmapDrawable) img.getDrawable()).getBitmap();
        return b;
    }
    protected void verifCarte(View v, int id){
        if(carte_prec == null) {
            carte_prec = v.findViewById(id);
        }
        else{
            ImageView img1 = v.findViewById(id);
            if(img1 != carte_prec){
                Bitmap bimg1 = convCarteOctet(img1);
                Bitmap bimg2 = convCarteOctet(carte_prec);
                if(bimg1 == bimg2){
                    paireCarte(img1);
                }
                else{
                    resetCarte(img1);
                }
            }else{
                resetCarte(img1);
            }

        }
    }
    protected void retournerCarte(View v, int id){
        if(id == R.id.image1){
            ImageView imageVue = v.findViewById(id);
            imageVue.setImageResource(listeCartes.get(0));
        }
        else if (id == R.id.image2){
            ImageView imageVue = v.findViewById(id);
            imageVue.setImageResource(listeCartes.get(1));
        }
        else if (id == R.id.image3){
            ImageView imageVue = v.findViewById(id);
            imageVue.setImageResource(listeCartes.get(2));
        }
        else if (id == R.id.image4){
            ImageView imageVue = v.findViewById(id);
            imageVue.setImageResource(listeCartes.get(3));
        }
        else if (id == R.id.image5){
            ImageView imageVue = v.findViewById(id);
            imageVue.setImageResource(listeCartes.get(4));
        }
        else if (id == R.id.image6){
            ImageView imageVue = v.findViewById(id);
            imageVue.setImageResource(listeCartes.get(5));
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        retournerCarte(v, id);
        verifCarte(v, id);
    }
}