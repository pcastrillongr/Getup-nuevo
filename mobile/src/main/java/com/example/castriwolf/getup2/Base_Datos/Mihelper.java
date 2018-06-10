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
                "lunes boolean," +
                "martes boolean," +
                "miercoles boolean," +
                "jueves boolean," +
                "viernes boolean," +
                "sabado boolean," +
                "domingo boolean)");

        db.execSQL("Create table Pending(" +
                "idPending Integer primary key autoincrement ," +
                "idAlarma Integer References Alarma(idAlarma) );");


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

    public ArrayList<Integer>diasAlarma(int hora,int minutos)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        ArrayList<Integer>dias=new ArrayList<Integer>();

        String query="select lunes,martes,miercoles,jueves,viernes,sabado,domingo from Alarma  where Hsalida="+String.valueOf(hora)+" and Msalida="+String.valueOf(minutos);

        Cursor cursor = db.rawQuery(query, null);
        int lunes=0;
        int martes=0;
        int miercoles=0;
        int jueves=0;
        int viernes=0;
        int sabado=0;
        int domingo=0;

        //if TABLE has rows
        if (cursor.moveToFirst()) {
            //Loop through the table rows
            do {
                lunes=(cursor.getInt(0));
                martes=(cursor.getInt(1));
                miercoles=(cursor.getInt(2));
                jueves=(cursor.getInt(3));
                viernes=(cursor.getInt(4));
                sabado=(cursor.getInt(5));
                domingo=(cursor.getInt(6));

            } while (cursor.moveToNext());
        }
        dias.add(lunes);
        dias.add(martes);
        dias.add(miercoles);
        dias.add(jueves);
        dias.add(viernes);
        dias.add(sabado);
        dias.add(domingo);



       return  dias;

    }

    public boolean insertarAlarma(String Lsalida, String Lllegada, int Hsalida, int Msalida, int Hllegad, int Mllegada,boolean lunes,boolean martes,boolean miercoles,boolean jueves,boolean viernes,boolean sabado,boolean domingo){
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

        String query="select nombreActividad,AVG(tiempo) from Actividad  group by nombreActividad";

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


    public void insertarPending(int idAlarma){


        ContentValues contentValues = new ContentValues();

        contentValues.put("idAlarma",idAlarma);

        this.getWritableDatabase().insert("Pending",null,contentValues);


    }

    public int recuperaridPending(){

        SQLiteDatabase db=this.getReadableDatabase();

        int idPending=0;

        String query="select idPending from Pending  order by idPending desc limit 1 ";

        Cursor cursor = db.rawQuery(query, null);

        //if TABLE has rows
        if (cursor.moveToFirst()) {
            //Loop through the table rows
            do {
                idPending=cursor.getInt(0);

            } while (cursor.moveToNext());
        }

        return  idPending;
    }


}
