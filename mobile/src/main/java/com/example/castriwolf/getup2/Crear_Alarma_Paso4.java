package com.example.castriwolf.getup2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.NumberPicker;

public class Crear_Alarma_Paso4 extends AppCompatActivity {

    NumberPicker picker;
    ImageView next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear__alarma__paso4);
        picker=(NumberPicker)findViewById(R.id.numberpicker);
        picker.setMaxValue(59);
        picker.setMinValue(0);
        next=(ImageView)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent go=new Intent(getApplicationContext(),Crear_Alarma_Paso5.class);
                startActivity(go);
            }
        });


    }
}
