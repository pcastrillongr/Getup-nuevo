package com.example.castriwolf.getup2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.NumberPicker;

public class Crear_Alarma_Paso4 extends AppCompatActivity {

    NumberPicker picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear__alarma__paso4);
        picker=(NumberPicker)findViewById(R.id.numberpicker);
        picker.setMaxValue(59);
        picker.setMinValue(0);
    }
}
