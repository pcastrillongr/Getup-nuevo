package com.example.castriwolf.getup2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class Menu_Alarma extends AppCompatActivity {

    private ImageView anhadiralarma;
    private ImageView menupreferencias;
    private ListView listView;
    private ArrayList<String> aux = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__alarma);
        anhadiralarma = findViewById(R.id.anhadir);
        menupreferencias = findViewById(R.id.menu);
        listView = findViewById(R.id.listview);


        /*if(!alarmas.isEmpty()) {
            rellenarArray();
            ArrayAdapter<String> arrayAdapter;
            arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, aux);
            listView.setAdapter(arrayAdapter);
        }*/

        anhadiralarma.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent go = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(go);

            }
        });
        menupreferencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Intent go = new Intent(getApplicationContext(), Preferencias_Alarma.class);
                //startActivity(go);

            }
        });

    }

    private void rellenarArray() {


        //aux.clear();
        //for (int i = 0; i < alarmas.size(); i++) {
        //  aux.add(String.valueOf(alarmas.get(i).getLugarSalida()));

    }


}
