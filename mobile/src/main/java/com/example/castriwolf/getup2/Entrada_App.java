package com.example.castriwolf.getup2;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.castriwolf.getup2.Base_Datos.Mihelper;

public class Entrada_App extends AppCompatActivity {

    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrada__app);
        Mihelper miHelper = new Mihelper(this,"miDB",1);
        SQLiteDatabase db = miHelper.getWritableDatabase();
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
