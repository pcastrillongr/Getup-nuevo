package com.example.castriwolf.getup2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.castriwolf.getup2.Fragmentos.Fragmento_Paso_1;

public class Crear_Alarma extends AppCompatActivity {

    private Button btnTiempo;
    private Button btnDistancia;
    private Button btnActividades;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear__alarma);

        btnTiempo=findViewById(R.id.btnTiempo);
        btnDistancia=findViewById(R.id.btnDistancia);
        btnActividades=findViewById(R.id.btnActividades);

        btnDistancia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(i);
            }
        });


    }


    public void tiempo(View view){

    }

    public void distancia(View view){


    }
}
