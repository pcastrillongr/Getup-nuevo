package com.example.castriwolf.getup2.Base_Datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Mihelper extends SQLiteOpenHelper {

    public Mihelper(Context context, String name, int version) {
        super(context, name, null, version);
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

        db.execSQL("Create table Alarma(idAlarma Integer primary key," +
                "Lsalida String," +
                "Lllegada String," +
                "Hsalida Integer," +
                "Msalida Integer," +
                "Hllegada Integer," +
                "Mllegada Integer," +
                "id_act Integer," +
                "foreign key (id_act) references Actividad(idAct));" +
                "");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
