package com.example.castriwolf.getup2.Clases;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.castriwolf.getup2.R;

import java.util.Calendar;

/**
 * Created by cristinavilas on 5/14/18.
 */

public class MyAlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        boolean lunes;
        boolean martes;
        boolean miercoles;
        boolean jueves;
        boolean viernes;
        boolean sabado;
        boolean domingo;
        Intent go=new Intent(context,MyNewIntentService.class);
        SharedPreferences pref= context.getSharedPreferences("preferenciasdias", context.MODE_PRIVATE);
        SharedPreferences.Editor editor=pref.edit();




        Calendar calendar=Calendar.getInstance();
       lunes=pref.getBoolean("lunes",false);
       martes=pref.getBoolean("martes",false);
       miercoles=pref.getBoolean("miercoles",false);
       jueves=pref.getBoolean("jueves",false);
       viernes=pref.getBoolean("viernes",false);
       sabado=pref.getBoolean("sabado",false);
       domingo=pref.getBoolean("domingo",false);
        int dia= calendar.get(Calendar.DAY_OF_WEEK);




        if(lunes&&dia==2) {

        context.startService(go);
    }
        if(martes&&dia==3) {

            context.startService(go);
        }
        if(miercoles&&dia==4) {

            context.startService(go);
        }
        if(jueves&&dia==5) {

            context.startService(go);
        }
        if(viernes&&dia==6) {

            context.startService(go);
        }
        if(sabado&&dia==7) {

            context.startService(go);
        }
        if(domingo&&dia==1) {

            context.startService(go);
        }
        pref.edit().clear().commit();


    }
}