package com.example.castriwolf.getup2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.castriwolf.getup2.Base_Datos.Mihelper;
import com.example.castriwolf.getup2.Clases.Alarma;
import com.example.castriwolf.getup2.Clases.Container;

import java.util.ArrayList;

import static com.example.castriwolf.getup2.Clases.Container.alarmas;

public class Menu_Alarma extends AppCompatActivity {

    private String[] opciones = {"Agenda", "En que gasto el tiempo", "Preferencias", "Sobre nosotros"};
    private DrawerLayout drawerLayout;
    private ImageView anhadiralarma;
    private ImageView menupreferencias;
    private ListView listViewDrawer;
    private ListView listView;
    private ArrayList<String> aux = new ArrayList<String>();
    private boolean datos=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__alarma);
        anhadiralarma = findViewById(R.id.anhadir);
        menupreferencias = findViewById(R.id.menu);
        listViewDrawer = findViewById(R.id.list_view);
        listView = findViewById(R.id.listview);

        drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.removeView(listViewDrawer);
        drawerLayout.addView(listViewDrawer);


        ArrayAdapter<String> adaptadorMenu;
        adaptadorMenu = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, opciones);
        listViewDrawer.setAdapter(adaptadorMenu);
        listViewDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent i = null;
                switch (position) {
                    case 0:
                        // i = new Intent(getApplicationContext(), AgendaActivity.class);
                        break;
                    case 1:
                        //i = new Intent(getApplicationContext(), HospitalesCercanos.class);
                        break;
                    case 2:
                        i = new Intent(getApplicationContext(), Preferencias_Alarma.class);
                        break;
                    case 3:


                        break;
                    case 4:
                        // i = new Intent(getApplicationContext(),GaleriaActivity.class);
                        break;
                }

                startActivity(i);
                drawerLayout.closeDrawer(listViewDrawer);
            }
        });
        comprobarAlarmas();
        if (datos==true) {

            rellenarArray();
            ArrayAdapter<String> arrayAdapter;
            arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, aux);
            listView.setAdapter(arrayAdapter);
        } else {
            comprobarAlarmas();

        }

        anhadiralarma.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent go = new Intent(getApplicationContext(), Crear_Alarma_Paso1.class);
                startActivity(go);

            }
        });
        menupreferencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                drawerLayout.openDrawer(listViewDrawer);

            }
        });

    }

    private void rellenarArray() {
        aux.clear();

        for (int i = 0; i < alarmas.size(); i++) {
            aux.add(String.valueOf(alarmas.get(i).getId_alarma()));
        }

    }

    private void comprobarAlarmas() {

        Mihelper mihelper = new Mihelper(getApplicationContext());
        SQLiteDatabase db = mihelper.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Alarma", null);


//Nos aseguramos de que existe al menos un registro
        try {
            if (c.moveToFirst()) {
                //Recorremos el cursor hasta que no haya más registros
                do {
                    int id_alarma = c.getInt(0);
                    String Lsalida = c.getString(1);
                    String Lllegada = c.getString(2);
                    int hSalida = c.getInt(3);
                    int mSalida = c.getInt(4);
                    int hLlegada = c.getInt(5);
                    int mLlegada = c.getInt(6);
                    Alarma alarma = new Alarma (id_alarma,Lsalida,Lllegada,hSalida,mSalida,hLlegada,mLlegada);
                    Container.alarmas.add(alarma);
                    datos = true;
                } while (c.moveToNext());

            }
        } catch (Exception ex) {

        }

        db.close();
    }

}