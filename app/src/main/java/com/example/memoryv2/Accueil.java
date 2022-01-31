package com.example.memoryv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Accueil extends AppCompatActivity implements View.OnClickListener{

    private Button boutonJouer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        boutonJouer = findViewById(R.id.button3);
    }

    @Override
    protected void onResume() {
        super.onResume();
        boutonJouer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id ==  R.id.button3){
            Intent intent = new Intent(Accueil.this,MainActivity.class);
            startActivity(intent);
        }
    }
}