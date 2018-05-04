package com.example.castriwolf.getup2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.NumberPicker;

public class Crear_Alarma_Paso3 extends AppCompatActivity {

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
    private String tiempoRecorrido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear__alarma__paso3);
        picker=(NumberPicker) findViewById(R.id.numberpicker);
        picker.setMinValue(0);
        picker.setMaxValue(59);
        next=(ImageView)findViewById(R.id.next);

        Bundle parametros =getIntent().getExtras();
        lunes=parametros.getBoolean("Lunes");
        martes=parametros.getBoolean("Martes");
        miercoles=parametros.getBoolean("Miercoles");
        jueves=parametros.getBoolean("Jueves");
        viernes=parametros.getBoolean("Viernes");
        sabado=parametros.getBoolean("Sabado");
        domingo=parametros.getBoolean("Domingo");

        hora = parametros.getInt("Hora");
        minuto=parametros.getInt("Hminuto");
        tiempoRecorrido=parametros.getString("TiempoRecorrido");




        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent go=new Intent(getApplicationContext(),Crear_Alarma_Paso4.class);
                go.putExtra("Lunes",lunes);
                go.putExtra("Martes",martes);
                go.putExtra("Miercoles",miercoles);
                go.putExtra("Jueves",jueves);
                go.putExtra("Viernes",viernes);
                go.putExtra("Sabado",sabado);
                go.putExtra("Domingo",domingo);

                //Hora llegada trabajo
                go.putExtra("Hora", hora);
                go.putExtra("HMinuto", minuto);
                //tiempo recorrido Maps
                go.putExtra("TiempoRecorrido", String.valueOf(tiempoRecorrido));
                //tiempo del
                go.putExtra("Tlevantarse",picker.getValue());


                startActivity(go);
            }
        });


    }
}
