package com.example.memoryv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Accueil extends AppCompatActivity implements View.OnClickListener{

    private Button boutonFruits = null;
    private Button boutonLegumes = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        boutonFruits = findViewById(R.id.button3);
        boutonLegumes = findViewById(R.id.button4);
    }

    @Override
    protected void onResume() {
        super.onResume();
        boutonFruits.setOnClickListener(this);
        boutonLegumes.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id ==  R.id.button3){
            Intent intent = new Intent(Accueil.this,MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("mode", 1);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if (id ==  R.id.button4){
            Intent intent = new Intent(Accueil.this,MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("mode", 2);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}