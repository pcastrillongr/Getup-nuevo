package com.example.castriwolf.getup2.Base_Datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.castriwolf.getup2.Clases.Alarma;
import com.google.gson.internal.bind.SqlDateTypeAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class Mihelper extends SQLiteOpenHelper {

    public static final String n_Database ="miDB";

    public Mihelper(Context context) {
        super(context, n_Database, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db ) {

        db.execSQL("Create table Lugares(" +
                "idLugar integer  primary key,Lugar String);" +
                "");

        db.execSQL("Create table Actividad(" +
                "idAct Integer  primary key autoincrement," +
                "nombreActividad String," +
                "tiempo integer);" +

                "");

        db.execSQL("Create table Alarma(" +
                "idAlarma Integer primary key autoincrement," +
                "Lsalida String," +
                "Lllegada String," +
                "Hsalida Integer," +
                "Msalida Integer," +
                "Hllegada Integer," +
                "Mllegada Integer," +
                "lunes Integer," +
                "martes Integer," +
                "miercoles Integer," +
                "jueves Integer," +
                "viernes Integer," +
                "sabado Integer," +
                "domingo Integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public void insertarActividad(String nombreActividad,int tiempo)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombreActividad",nombreActividad);
        contentValues.put("tiempo",tiempo);

        this.getWritableDatabase().insert("Actividad",null,contentValues);




    }

    public boolean insertarAlarma(String Lsalida, String Lllegada, int Hsalida, int Msalida, int Hllegad, int Mllegada,int lunes,int martes,int miercoles,int jueves,int viernes,int sabado,int domingo){
        ContentValues contentValues = new ContentValues();
        contentValues.put("Lsalida",Lsalida);
        contentValues.put("Lllegada",Lllegada);
        contentValues.put("Hsalida",Hsalida);
        contentValues.put("Msalida",Msalida);
        contentValues.put("Hllegada",Hllegad);
        contentValues.put("Mllegada",Mllegada);
        contentValues.put("Lunes",lunes);
        contentValues.put("Martes",martes);
        contentValues.put("Miercoles",miercoles);
        contentValues.put("Jueves",jueves);
        contentValues.put("Viernes",viernes);
        contentValues.put("Sabado",sabado);
        contentValues.put("Domingo",domingo);
        long result = this.getWritableDatabase().insert("Alarma",null,contentValues);

        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }
    public void eliminarAlarma(int hora,int minutos)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("delete from Alarma where Hsalida="+String.valueOf(hora)+" and Msalida="+ String.valueOf(minutos));

    }
    public void eliminarTodaslasAlarmas()
    {

        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("delete from Alarma");

    }

    public int getIDAlarma()
    {
        SQLiteDatabase db=this.getReadableDatabase();

        int idalarma=0;

        String query="select idAlarma from Alarma  order by idalarma desc limit 1 ";

        Cursor cursor = db.rawQuery(query, null);

        //if TABLE has rows
        if (cursor.moveToFirst()) {
            //Loop through the table rows
            do {
                idalarma=cursor.getInt(0);

            } while (cursor.moveToNext());
        }

        return  idalarma;

    }

    public HashMap<String,Integer> getMediaTiempos()
    {
        SQLiteDatabase db=this.getReadableDatabase();

        HashMap<String,Integer>nombresytiempos=new HashMap<String, Integer>();
        String actividad;
        int tiempo;

        String query="select nombreActividad,AVG(tiempo) from Actividad  where nombreActividad like 'tiempolevantarse' " +
                "and 'tiempobaño' and 'tiempodesayuno' and 'tiempootros' and 'tiemporecorrido' group by nombreActividad";

        Cursor cursor = db.rawQuery(query, null);

        //if TABLE has rows
        if (cursor.moveToFirst()) {
            //Loop through the table rows
            do {

                actividad=cursor.getString(0);
                tiempo=cursor.getInt(1);
                nombresytiempos.put(actividad,tiempo);


            } while (cursor.moveToNext());
        }


        return  nombresytiempos;

    }



}
