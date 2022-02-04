package com.example.memoryv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class Accueil extends AppCompatActivity implements View.OnClickListener{

    private Button boutonFruits = null;
    private Button boutonLegumes = null;
    private Switch switchMusique = null;

    private MediaPlayer son_click;
    private MediaPlayer theme_jeu;
    private boolean statut_musique = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        boutonFruits = findViewById(R.id.button3);
        boutonLegumes = findViewById(R.id.button4);
        switchMusique = findViewById(R.id.switch2);
        son_click = MediaPlayer.create(this,R.raw.click);
        theme_jeu = MediaPlayer.create(this,R.raw.crash_theme);
    }

    @Override
    protected void onResume() {
        super.onResume();
        boutonFruits.setOnClickListener(this);
        boutonLegumes.setOnClickListener(this);
        switchMusique.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        boolean checked = ((Switch) v).isChecked();
        if (id ==  R.id.button3){
            son_click.start();
            theme_jeu.stop();
            Intent intent = new Intent(Accueil.this,MainActivity.class);
            Bundle bundle = new Bundle();
            //Choix du mode fruits
            bundle.putSerializable("mode", 1);
            bundle.putSerializable("statut_musique",statut_musique);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if (id ==  R.id.button4){
            son_click.start();
            theme_jeu.stop();
            Intent intent = new Intent(Accueil.this,MainActivity.class);
            Bundle bundle = new Bundle();
            //Choix du mode légumes
            bundle.putSerializable("mode", 2);
            bundle.putSerializable("statut_musique",statut_musique);
            intent.putExtras(bundle);
            startActivity(intent);
        }


        if (checked){
            if(!theme_jeu.isPlaying()){
                theme_jeu.start();
                statut_musique = true;
            }
        }
        else{
            if(theme_jeu.isPlaying()){
                theme_jeu.stop();
                statut_musique = false;
            }
        }

    }
}