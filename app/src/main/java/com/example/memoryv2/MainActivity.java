package com.example.memoryv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;
    private ImageView image5;
    private ImageView image6;

    private int nbPaire = 0;
    private int nbCoups = 0;
    private int mode = 1;
    private int statut_musique;

    private List<String> toast_gg = null;
    private Resources resources = null;

    private Toast mToastToShow;

    private MediaPlayer son_click;
    private MediaPlayer son_paire_carte;
    private MediaPlayer son_pas_paire_carte;

    private ImageView carte_prec = null;
    Integer[] tabFruits = {R.drawable.ananas, R.drawable.banane, R.drawable.cerise, R.drawable.citron, R.drawable.fraise, R.drawable.kiwi, R.drawable.mandarine, R.drawable.mangue, R.drawable.melon, R.drawable.myrtille, R.drawable.noixcoco, R.drawable.olive, R.drawable.pasteque, R.drawable.peche, R.drawable.poire, R.drawable.pomme, R.drawable.pommev, R.drawable.raisin, R.drawable.tomate};
    Integer[] tabLegumes = {R.drawable.ail, R.drawable.aubergine, R.drawable.avocat, R.drawable.brocoli, R.drawable.cachuette, R.drawable.carotte, R.drawable.champignon, R.drawable.chataigne, R.drawable.concombre, R.drawable.mais, R.drawable.oignon, R.drawable.patate, R.drawable.piment, R.drawable.poivron, R.drawable.salade};
    private List<Integer> listeCartes = null;

    protected void choixTheme(){
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            mode = (int) bundle.getSerializable("mode");
            Integer[] tabTMP = new Integer[6];
            statut_musique = (int) bundle.getSerializable("statut_musique");
            if (mode == 1){
                for (int i = 0; i < tabTMP.length; i += 2){
                    int random = new Random().nextInt(tabFruits.length);
                    tabTMP[i] = tabFruits[random];
                    tabTMP[i+1] = tabFruits[random];
                }
                listeCartes = Arrays.asList(tabTMP);
            }
            else if (mode == 2){
                for (int i = 0; i < tabTMP.length; i += 2){
                    int random = new Random().nextInt(tabLegumes.length);
                    tabTMP[i] = tabLegumes[random];
                    tabTMP[i+1] = tabLegumes[random];
                }
                listeCartes = Arrays.asList(tabTMP);
            }
        }
    }

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
        son_click = MediaPlayer.create(this,R.raw.click);
        son_paire_carte = MediaPlayer.create(this,R.raw.good_match);
        son_pas_paire_carte= MediaPlayer.create(this,R.raw.wrong_match);
        resources = getResources();
        toast_gg = new ArrayList<>(Arrays.asList(resources.getStringArray(R.array.toast_gg)));
        choixTheme();
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
    protected void paireCarte(View view, ImageView img){
        img.setOnClickListener(null);
        carte_prec.setOnClickListener(null);
        showToast(view, toast_gg.get(i));
        //Toast.makeText(MainActivity.this,toast_gg.get(i), Toast.LENGTH_SHORT).show();
        i += 1;
        carte_prec = null;
    }
    protected void resetCarte(View view, ImageView img){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                img.setImageResource(R.drawable.backcard);
                carte_prec.setImageResource(R.drawable.backcard);
                carte_prec = null;
                showToast(view, toast_gg.get(3));
                if(statut_musique == 1){
                    son_pas_paire_carte.start();
                }
                //Toast.makeText(MainActivity.this,toast_gg.get(3), Toast.LENGTH_SHORT).show();
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
            nbCoups += 1;
            ImageView img1 = v.findViewById(id);
            if(img1 != carte_prec){
                Bitmap bimg1 = convCarteOctet(img1);
                Bitmap bimg2 = convCarteOctet(carte_prec);
                if(bimg1 == bimg2){
                    if(statut_musique == 1){
                        son_paire_carte.start();
                    }
                    paireCarte(v, img1);
                    nbPaire += 1;
                    if (nbPaire == 3){
                        finJeu();
                    }
                }
                else{
                    resetCarte(v, img1);
                }
            }else{
                resetCarte(v, img1);
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
    protected void finJeu(){
        Intent intent = new Intent(MainActivity.this,EndGame.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("nbCoups", nbCoups);
        bundle.putSerializable("statut_musique",statut_musique);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        son_click.start();
        retournerCarte(v, id);
        verifCarte(v, id);
    }

    public void showToast(View view, String str) {
        // Set the toast and duration
        int toastDurationInMilliSeconds = 750;
        mToastToShow = Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG);

        // Set the countdown to display the toast
        CountDownTimer toastCountDown;
        toastCountDown = new CountDownTimer(toastDurationInMilliSeconds, 1000 /*Tick duration*/) {
            public void onTick(long millisUntilFinished) {
                mToastToShow.show();
            }

            public void onFinish() {
                mToastToShow.cancel();
            }
        };

        // Show the toast and starts the countdown
        mToastToShow.show();
        toastCountDown.start();
    }
}