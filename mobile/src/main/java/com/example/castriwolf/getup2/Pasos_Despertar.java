package com.example.castriwolf.getup2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Toast;

public class Pasos_Despertar extends AppCompatActivity {

    ImageView guardar;
    NumberPicker pasos;
    SharedPreferences pref;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.pasos_despertar);
        pref=getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
        editor=pref.edit();
        guardar = (ImageView) findViewById(R.id.guardar);
        pasos = findViewById(R.id.numberpickerpasos);
        pasos.setMinValue(10);
        pasos.setMaxValue(50);
        pasos.setValue(pref.getInt("pasos",30));




        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                editor.putInt("pasos",pasos.getValue());
                Toast.makeText(getApplicationContext(),"Pasos Guardados",Toast.LENGTH_SHORT).show();
                Intent go = new Intent(getApplicationContext(), Preferencias_Alarma.class);
                startActivity(go);
            }
        });
    }
}


