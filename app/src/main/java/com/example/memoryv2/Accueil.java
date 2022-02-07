package com.example.memoryv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;

import java.net.URL;

public class Accueil extends AppCompatActivity implements View.OnClickListener{

    private Button boutonFruits = null;
    private Button boutonLegumes = null;
    private ImageView eseo;
    private Switch switchMusique = null;

    private MediaPlayer son_click;
    private MediaPlayer theme_jeu;
    private int statut_musique = 0;

    Intent intent;
    Intent intent_eseo;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        boutonFruits = findViewById(R.id.button3);
        boutonLegumes = findViewById(R.id.button4);
        eseo = findViewById(R.id.eseo);
        switchMusique = findViewById(R.id.switch2);
        son_click = MediaPlayer.create(this,R.raw.click);
        theme_jeu = MediaPlayer.create(this,R.raw.crash_theme);

        intent = new Intent(Accueil.this,MainActivity.class);
        intent_eseo = new Intent(android.content.Intent.ACTION_VIEW);

        bundle = new Bundle();
    }

    @Override
    protected void onResume() {
        super.onResume();
        boutonFruits.setOnClickListener(this);
        boutonLegumes.setOnClickListener(this);
        eseo.setOnClickListener(this);
        switchMusique.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        int checked = 0;

        if(id == R.id.eseo){
            intent_eseo.setData(Uri.parse("https://www.eseo.fr"));
            startActivity(intent_eseo);
        }

        if (id == R.id.switch2) {
            checked = Boolean.hashCode(switchMusique.isChecked());
        }

        if (id ==  R.id.button3){
            son_click.start();

            //Choix du mode fruits
            bundle.putSerializable("mode", 1);
            bundle.putSerializable("statut_musique",statut_musique);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if (id ==  R.id.button4){
            son_click.start();

            //Choix du mode légumes
            bundle.putSerializable("mode", 2);
            bundle.putSerializable("statut_musique",statut_musique);
            intent.putExtras(bundle);
            startActivity(intent);
        }


        if (checked == 1231){
            if(!theme_jeu.isPlaying()){
                theme_jeu.setLooping(true);
                theme_jeu.start();
            }
            statut_musique = 1;
        }
        else{
            if(theme_jeu.isPlaying()){
                theme_jeu.pause();
            }
            statut_musique = 0;
        }

    }
}