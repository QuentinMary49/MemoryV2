package com.example.memoryv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EndGame extends AppCompatActivity implements View.OnClickListener {

    private int nbCoups;
    private TextView messageCoup = null;
    private Button boutonRejouer = null;
    private Button boutonAccueil = null;
    private ImageView ralph;
    private int statut_musique;

    private Intent intent_web;

    private MediaPlayer son_victoire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
        messageCoup = findViewById(R.id.textView);
        boutonRejouer = findViewById(R.id.button);
        boutonAccueil = findViewById(R.id.button2);
        ralph = findViewById(R.id.ralph);
        son_victoire = MediaPlayer.create(this, R.raw.finish);

    }

    @Override
    protected void onResume() {
        super.onResume();
        boutonRejouer.setOnClickListener(this);
        boutonAccueil.setOnClickListener(this);
        ralph.setOnClickListener(this);
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            nbCoups = (int) bundle.getSerializable("nbCoups");
            statut_musique = (int) bundle.getSerializable("statut_musique");
            messageCoup.setText("NOMBRE DE COUPS : "+nbCoups);
            if(statut_musique == 1){
                son_victoire.start();
            }

        }
    }

    @Override
    public void onClick(View v) {
        son_victoire.stop();
        int id =  v.getId();
        if (id ==  R.id.button){
            Intent intent = new Intent(EndGame.this,MainActivity.class);
            startActivity(intent);
        }
        if (id ==  R.id.button2){
            Intent intent = new Intent(EndGame.this,Accueil.class);
            startActivity(intent);
        }
        if(id == R.id.ralph){
            intent_web = new Intent(android.content.Intent.ACTION_VIEW);
            intent_web.setData(Uri.parse("https://bit.ly/3smVzCz"));
            startActivity(intent_web);
        }
    }
}