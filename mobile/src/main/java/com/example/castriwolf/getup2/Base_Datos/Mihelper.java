package com.example.castriwolf.getup2.Base_Datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
                "idAct Integer  primary key," +
                "razon String,tiempo Integer," +
                "idAl integer," +
                "foreign key(idAl)references Alarma(idAlarma));" +
                "");

        db.execSQL("Create table Alarma(idAlarma Integer primary key autoincrement,Lsalida String,Lllegada String,Hsalida Integer,Msalida Integer,Hllegada Integer,Mllegada Integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public boolean insertarAlarma(String Lsalida, String Lllegada, int Hsalida, int Msalida, int Hllegad, int Mllegada){
        ContentValues contentValues = new ContentValues();
        contentValues.put("Lsalida",Lsalida);
        contentValues.put("Lllegada",Lllegada);
        contentValues.put("Hsalida",Hsalida);
        contentValues.put("Msalida",Msalida);
        contentValues.put("Hllegad",Hllegad);
        contentValues.put("Mllegada",Mllegada);
        long result = this.getWritableDatabase().insert("Alarma",null,contentValues);

        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }


}
