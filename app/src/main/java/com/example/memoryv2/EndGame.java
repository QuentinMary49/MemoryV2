package com.example.memoryv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndGame extends AppCompatActivity implements View.OnClickListener {

    private int nbCoups;
    private TextView messageCoup = null;
    private Button boutonRejouer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
        messageCoup = findViewById(R.id.textView);
        boutonRejouer = findViewById(R.id.button);
    }

    @Override
    protected void onResume() {
        super.onResume();
        boutonRejouer.setOnClickListener(this);
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            nbCoups = (int) bundle.getSerializable("nbCoups");
            messageCoup.setText("NOMBRE DE COUPS : "+nbCoups);
        }
    }

    @Override
    public void onClick(View v) {
        int id =  v.getId();
        if (id ==  R.id.button){
            Intent intent = new Intent(EndGame.this,MainActivity.class);
            startActivity(intent);
        }
    }
}