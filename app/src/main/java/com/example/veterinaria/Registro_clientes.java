package com.example.veterinaria;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Registro_clientes extends AppCompatActivity {

    EditText etNombres, etApellidos, etDni, etClaveacceso;
    String nombres, apellidos, dni , claveacceso;
    Button btRegistrarse;

    final String  URL = "http://192.168.1.36/Wveterinaria/controllers/veterinaria.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_clientes);

        loadUI();

        btRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarCajas();
            }
        });
    }


    private void validarCajas(){
        nombres = etNombres.getText().toString().trim();
        apellidos = etApellidos.getText().toString().trim();
        dni = etDni.getText().toString().trim();
        claveacceso = etClaveacceso.getText().toString().trim();

        if(nombres.isEmpty()){
            etNombres.setError("Complete el campo");
        } else if (apellidos.isEmpty()) {
            etApellidos.setError("Complete el campo");
        } else if (dni.isEmpty()) {
            etDni.setError("Complete el campo");
        } else if (claveacceso.isEmpty()) {
            etClaveacceso.setError("Complete el campo");
        }else {
            mostrarDialog();

        }

    }

    private void mostrarDialog(){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle("Clientes");
        dialogo.setMessage("¿Está seguro de registrarse?");
        dialogo.setCancelable(false);

        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                registrarClientes();
            }
        });

        dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        dialogo.show();
    }

    private  void registrarClientes() {
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Comunicación exitosa
                if (response.trim().equals("")) {
                    resetUI();
                    etNombres.requestFocus();
                    Toast.makeText(getApplicationContext(), "Guardado correctamente", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.d("Error", error.toString());
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("operacion", "addClientes");
                parametros.put("nombres", nombres);
                parametros.put("apellidos", apellidos);
                parametros.put("dni", dni);
                parametros.put("claveacceso", claveacceso);
                return parametros;

            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void resetUI(){
        etApellidos.setText("");
        etNombres.setText("");
        etDni.setText("");
        etClaveacceso.setText("");

    }


    private void loadUI(){
        etNombres = findViewById(R.id.etNombres);
        etApellidos = findViewById(R.id.etApellidos);
        etDni = findViewById(R.id.etDni);
        etClaveacceso = findViewById(R.id.etClaveacceso);
        btRegistrarse = findViewById(R.id.btRegistrarse);
    }
}