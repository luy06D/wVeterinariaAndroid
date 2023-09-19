package com.example.veterinaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    TextView etlinkRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loadUI();
        etlinkRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarRegistro();
            }
        });
    }


    private void mostrarRegistro(){
        Intent intent = new Intent(getApplicationContext(), Registro_clientes.class );
        startActivity(intent);
    }

    private void loadUI(){
        etlinkRegistrar = findViewById(R.id.etlinkRegistrar);
    }
}