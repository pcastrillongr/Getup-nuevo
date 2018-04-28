package com.example.castriwolf.getup2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.ToggleButton;

public class Crear_Alarma_Paso1 extends AppCompatActivity {

    ImageButton toggleButton1;
    ImageButton toggleButton2;
    ImageButton toggleButton3;
    ImageButton toggleButton4;
    ImageButton toggleButton5;
    ImageButton toggleButton6;
    ImageButton toggleButton7;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_alarma_paso1);

        toggleButton1=(ImageButton) findViewById(R.id.toggleButton8);
        toggleButton2=(ImageButton) findViewById(R.id.toggleButton9);
        toggleButton3=(ImageButton) findViewById(R.id.toggleButton10);
        toggleButton4=(ImageButton) findViewById(R.id.toggleButton11);
        toggleButton5=(ImageButton) findViewById(R.id.toggleButton12);
        toggleButton6=(ImageButton) findViewById(R.id.toggleButton13);
        toggleButton7=(ImageButton) findViewById(R.id.toggleButton14);

     toggleButton1.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             toggleButton1.setImageResource(R.drawable.monday2);
             



         }
     });
        toggleButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleButton2.setImageResource(R.drawable.tueday2);


            }
        });
        toggleButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleButton3.setImageResource(R.drawable.wednesday2);


            }
        });  toggleButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleButton4.setImageResource(R.drawable.thursday2);


            }
        });  toggleButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleButton5.setImageResource(R.drawable.friday2);


            }
        });
        toggleButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleButton6.setImageResource(R.drawable.saturday2);


            }
        });  toggleButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleButton7.setImageResource(R.drawable.sunday2);


            }
        });




            toggleButton2.setImageResource(R.drawable.tueday2);
            toggleButton3.setImageResource(R.drawable.wednesday2);
            toggleButton4.setImageResource(R.drawable.thursday2);
            toggleButton5.setImageResource(R.drawable.friday2);
            toggleButton6.setImageResource(R.drawable.saturday2);
            toggleButton7.setImageResource(R.drawable.sunday2);
        }



    public void tiempo(View view){

    }

    public void distancia(View view){


    }
}
