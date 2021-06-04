package com.dim.retrofitjsonlogindb.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.dim.retrofitjsonlogindb.R;

public class Tablero extends AppCompatActivity {
    TextView tvUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablero);

        tvUsuario = (TextView)findViewById(R.id.tvUsuario);
        Intent intent = getIntent();
        if(intent.getExtras() != null){
            String passedUsuario = intent.getStringExtra("data");
            tvUsuario.setText("Bienvenido: " + passedUsuario);
        }
    }
}