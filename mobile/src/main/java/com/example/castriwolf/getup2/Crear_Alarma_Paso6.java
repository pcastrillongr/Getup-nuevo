package com.example.castriwolf.getup2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.NumberPicker;

public class Crear_Alarma_Paso6 extends AppCompatActivity {

    private NumberPicker picker;
    private ImageView next;
    private boolean lunes;
    private boolean martes;
    private boolean miercoles;
    private boolean jueves;
    private boolean viernes;
    private boolean sabado;
    private boolean domingo;
    private int hora;
    private int minuto;
    private int horaRecorrido;
    private int minutosRecorrido;
    private int Tlevantarse;
    private int Tbano;
    private int Tdesayuno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear__alarma__paso6);


        picker = findViewById(R.id.numberpicker4);
        picker.setMaxValue(59);
        picker.setMinValue(0);
        next = findViewById(R.id.next);

        recogerDatos();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                crearAlarma();

                Intent go = new Intent(getApplicationContext(), Menu_Alarma.class);
                startActivity(go);
            }
        });
    }

    private void recogerDatos(){
        Bundle parametros = getIntent().getExtras();
        lunes = parametros.getBoolean("Lunes");
        martes = parametros.getBoolean("Martes");
        miercoles = parametros.getBoolean("Miercoles");
        jueves = parametros.getBoolean("Jueves");
        viernes = parametros.getBoolean("Viernes");
        sabado = parametros.getBoolean("Sabado");
        domingo = parametros.getBoolean("Domingo");
        hora = parametros.getInt("Hora");
        minuto = parametros.getInt("HMinuto");
        horaRecorrido = parametros.getInt("HorasRecorrido");
        minutosRecorrido = parametros.getInt("MinutosRecorridos");
        Tlevantarse = parametros.getInt("Tlevantarse");
        Tbano = parametros.getInt("Tbaño");
        Tdesayuno *= parametros.getInt("Tdesayuno");
    }

    private void crearAlarma() {

        

    }
}
