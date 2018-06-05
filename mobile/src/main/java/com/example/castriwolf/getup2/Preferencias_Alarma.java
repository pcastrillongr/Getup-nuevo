package com.example.castriwolf.getup2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.castriwolf.getup2.Base_Datos.Mihelper;
import com.example.castriwolf.getup2.Clases.ListViewInflater;

public class Preferencias_Alarma extends AppCompatActivity {


    Button saliralarma;
    Switch vibracion;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    boolean vib;
    static int contador=0;
    Mihelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias__alarma);

                setContentView(R.layout.activity_preferencias__alarma);
                db=new Mihelper(getApplicationContext());
                pref=getSharedPreferences("Mispreferencias", Context.MODE_PRIVATE);
                editor=pref.edit();
                saliralarma=(Button)findViewById(R.id.salir);
                vibracion=(Switch)findViewById(R.id.switch5);
                vibracion.setChecked(pref.getBoolean("vibracion",false));

                vibracion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(vibracion.isChecked())
                        {
                            Toast.makeText(getApplicationContext(),"Vibracion Activada",Toast.LENGTH_SHORT).show();
                            editor.putBoolean("vibracion",true);

                        }else
                        {
                            Toast.makeText(getApplicationContext(),"Vibracion Desactivada",Toast.LENGTH_SHORT).show();
                            editor.putBoolean("vibracion",false);

                        }
                        editor.commit();
                    }
                });


                //alert dialog que nos preguntara si deseamos salir de la alarma
                saliralarma.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        AlertDialog.Builder builder;

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            builder = new AlertDialog.Builder(Preferencias_Alarma.this);
                        } else {
                            builder = new AlertDialog.Builder(Preferencias_Alarma.this);
                        }
                        builder.setView(R.layout.custom_layout);
                        builder.setTitle("Borrar Alarmas!");
                        builder.setMessage("Estás seguro que deseas borrar todas las alarmas?");
                        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                db.eliminarTodaslasAlarmas();
                                ListViewInflater.listAlarmas.clear();
                                Menu_Alarma.listView.invalidateViews();
                                Toast.makeText(getApplicationContext(),"Alarmas borradas",Toast.LENGTH_SHORT).show();

                                        }
                        });
                        builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setIcon(R.drawable.alarma_copy);
                        builder.show();

                    }
                });

            }
        }