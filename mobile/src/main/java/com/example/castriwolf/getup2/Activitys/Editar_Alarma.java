package com.example.castriwolf.getup2.Activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TimePicker;

import com.example.castriwolf.getup2.Base_Datos.Mihelper;
import com.example.castriwolf.getup2.R;

import java.util.ArrayList;

public class Editar_Alarma extends AppCompatActivity {

    TimePicker tp;
    SharedPreferences sharedPreferences;
    Mihelper bd;
    CheckBox ch1;
    CheckBox ch2;
    CheckBox ch3;
    CheckBox ch4;
    CheckBox ch5;
    CheckBox ch6;
    CheckBox ch7;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_alarma);
        bd=new Mihelper(getApplicationContext());
        tp=findViewById(R.id.timePicker3);
        tp.setIs24HourView(true);
        ch1=findViewById(R.id.checkBox);
        ch2=findViewById(R.id.checkBox2);
        ch3=findViewById(R.id.checkBox3);
        ch4=findViewById(R.id.checkBox4);
        ch5=findViewById(R.id.checkBox5);
        ch6=findViewById(R.id.checkBox6);
        ch7=findViewById(R.id.checkBox7);



        sharedPreferences=getSharedPreferences("Editar", Context.MODE_PRIVATE);
        tp.setHour(sharedPreferences.getInt("horaeditar",00));
        tp.setMinute(sharedPreferences.getInt("minutoseditar",00));
        int horaalarma=sharedPreferences.getInt("horaalarmaeditar",0);
        int minutosalarma=sharedPreferences.getInt("minutosalarmaeditar",0);

        ArrayList<Integer>diasalarma=bd.diasAlarma(horaalarma,minutosalarma);


        if(diasalarma.get(0)==1)
        {
            ch1.setChecked(true);
        }
        if(diasalarma.get(1)==1)
        {
            ch2.setChecked(true);
        }

        if(diasalarma.get(2)==1)
        {
            ch3.setChecked(true);
        }

        if(diasalarma.get(3)==1)
        {
            ch4.setChecked(true);
        }
        if(diasalarma.get(4)==1)
        {
            ch5.setChecked(true);
        }

        if(diasalarma.get(5)==1)
        {
            ch6.setChecked(true);
        }

        if(diasalarma.get(6)==1)
        {
            ch7.setChecked(true);
        }


        if(sharedPreferences.getInt("luneseditar",0)==0)
        {

        }
        if(sharedPreferences.getInt("marteseditar",0)==0)
        {

        }

        if(sharedPreferences.getInt("miercoleseditar",0)==0)
        {

        }

        if(sharedPreferences.getInt("jueveseditar",0)==0)
        {

        }

        if(sharedPreferences.getInt("vierneseditar",0)==0)
        {

        }

        if(sharedPreferences.getInt("sabadoeditar",0)==0)
        {

        }

        if(sharedPreferences.getInt("domingoeditar",0)==0)
        {

        }




    }

    @Override
    public void onBackPressed()
    {

        Intent go=new Intent(getApplicationContext(),Menu_Alarma.class);
        startActivity(go);
    }
}
