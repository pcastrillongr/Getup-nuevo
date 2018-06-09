package com.example.castriwolf.getup2.Activitys;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.castriwolf.getup2.Base_Datos.UsoBD;
import com.example.castriwolf.getup2.R;

public class Entrada_App extends AppCompatActivity {

    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrada__app);
        UsoBD.iniciarDb(this);
        imageView = findViewById(R.id.imageViewInicio);
        countTimer();


    }


    private void menuInicio() {

        Intent go=new Intent(this,Menu_Alarma.class);
        startActivity(go);
    }

    private void countTimer() {

        new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long l) {


            }

            @Override
            public void onFinish() {

                menuInicio();
            }
        }.start();
    }
}