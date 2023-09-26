package com.example.veterinaria;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.net.eap.EapSessionConfig;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Consulta extends AppCompatActivity {
    EditText etCodDni;
    Button btConsultar;

    final String URL = "http://192.168.1.36/Wveterinaria/controllers/veterinaria.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        loadUI();

        btConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultarMascota();
            }
        });
    }

    private void consultarMascota(){
        String codDni = etCodDni.getText().toString().trim();

        if(codDni.isEmpty()){
            Toast.makeText(getApplicationContext(), "Escriba su numero de DNI por favor", Toast.LENGTH_SHORT).show();

        }else {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("Resultado", response);

                }
            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("Error", error.toString());
                        }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> parametros = new HashMap<String, String>();
                    parametros.put("op","buscarMascota");
                    parametros.put("dni", codDni);
                    return parametros;
                }

            };

            Volley.newRequestQueue(this).add(stringRequest);

        }

    }
    private void loadUI(){
        etCodDni = findViewById(R.id.etCodDni);
        btConsultar = findViewById(R.id.btConsultar);
    }
}