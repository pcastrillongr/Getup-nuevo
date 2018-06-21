package com.example.castriwolf.getup2.Activitys;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.castriwolf.getup2.Base_Datos.Mihelper;
import com.example.castriwolf.getup2.Clases.Container;
import com.example.castriwolf.getup2.Clases.ListViewInflater;
import com.example.castriwolf.getup2.Clases.MyAlarmReceiver;
import com.example.castriwolf.getup2.Clases.Pending;
import com.example.castriwolf.getup2.R;

import java.util.ArrayList;
import java.util.Calendar;

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
    ImageView save;
    int horaalarma;
    int minutosalarma;
    int idalarma;


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
        save=findViewById(R.id.guardareditado);





        sharedPreferences=getSharedPreferences("Editar", Context.MODE_PRIVATE);
        tp.setHour(sharedPreferences.getInt("horaalarmaeditar",00));
        tp.setMinute(sharedPreferences.getInt("minutosalarmaeditar",00));
         horaalarma=sharedPreferences.getInt("horaalarmaeditar",0);
         minutosalarma=sharedPreferences.getInt("minutosalarmaeditar",0);
         idalarma=sharedPreferences.getInt("idalarmaeditar",0);


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




        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                for(Pending pi:Container.pendings)
                {
                    if(pi.getIdAlarma()==idalarma)
                    {
                        Intent intent = new Intent(getApplicationContext(), MyAlarmReceiver.class);
                        PendingIntent sender = PendingIntent.getBroadcast(getApplicationContext(), pi.getIdPending(), intent, 0);
                        AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                        alarmManager.cancel(sender);
                    }


                }

                Intent intent = new Intent(getApplicationContext(), MyAlarmReceiver.class);
                Mihelper db=new Mihelper(getApplicationContext());
                db.insertarPending(idalarma);
                db.editAlarm(idalarma,tp.getHour(),tp.getMinute(),ch1.isChecked(),ch2.isChecked(),ch3.isChecked(),ch4.isChecked(),ch5.isChecked(),ch6.isChecked(),ch7.isChecked());
                AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),db.recuperaridPending(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

                Calendar time=Calendar.getInstance();
                time = Calendar.getInstance();
                time.set(Calendar.HOUR_OF_DAY, tp.getHour());
                time.set(Calendar.MINUTE, tp.getMinute());
                time.set(Calendar.SECOND, 0);
                time.set(Calendar.MILLISECOND, 0);

                if(Calendar.getInstance().after(time)){//if its in the past increment
                    time.add(Calendar.DATE,1);
                }



                alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, time.getTimeInMillis(),AlarmManager.INTERVAL_DAY, pendingIntent);


                Toast.makeText(getApplicationContext(),"Alarma Editada",Toast.LENGTH_SHORT).show();
                Intent go=new Intent(getApplicationContext(),Menu_Alarma.class);
                startActivity(go);
            }
        });




    }

    @Override
    public void onBackPressed()
    {

        Intent go=new Intent(getApplicationContext(),Menu_Alarma.class);
        startActivity(go);
    }
}
