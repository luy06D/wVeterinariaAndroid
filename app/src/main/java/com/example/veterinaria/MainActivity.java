package com.example.veterinaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadUI();

        btIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarActivity();
            }
        });
    }

    private void mostrarActivity(){
        Intent intent = new Intent(getApplicationContext(), Login.class );
        startActivity(intent);
    }

    private void loadUI(){
        btIniciar = findViewById(R.id.btIniciar);
    }
}