package com.example.castriwolf.getup2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.TimePicker;

public class Crear_Alarma_Paso3 extends AppCompatActivity {

    NumberPicker picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear__alarma__paso3);
        picker=(NumberPicker) findViewById(R.id.numberpicker);
        picker.setMinValue(0);
        picker.setMaxValue(59);


    }
}
