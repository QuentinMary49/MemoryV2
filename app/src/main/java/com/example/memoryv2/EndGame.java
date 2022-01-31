package com.example.memoryv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndGame extends AppCompatActivity implements View.OnClickListener {

    private int nbCoups;
    private TextView messageCoup = null;
    private Button boutonRejouer = null;
    private Button boutonAccueil = null;

    private MediaPlayer son_victoire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
        messageCoup = findViewById(R.id.textView);
        boutonRejouer = findViewById(R.id.button);
        boutonAccueil = findViewById(R.id.button2);
        son_victoire = MediaPlayer.create(this, R.raw.finish);
    }

    @Override
    protected void onResume() {
        super.onResume();
        son_victoire.start();
        boutonRejouer.setOnClickListener(this);
        boutonAccueil.setOnClickListener(this);
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            nbCoups = (int) bundle.getSerializable("nbCoups");
            messageCoup.setText("NOMBRE DE COUPS : "+nbCoups);
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
    }
}