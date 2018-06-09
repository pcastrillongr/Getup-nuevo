package com.example.castriwolf.getup2.Activitys;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.castriwolf.getup2.Base_Datos.Mihelper;
import com.example.castriwolf.getup2.Clases.Alarma;
import com.example.castriwolf.getup2.Clases.Container;
import com.example.castriwolf.getup2.Clases.ListViewInflater;
import com.example.castriwolf.getup2.Clases.MyAlarmReceiver;
import com.example.castriwolf.getup2.R;

public class Preferencias_Alarma extends AppCompatActivity {


    ImageView borrar;
    Switch vibracion;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    ImageView editar;
    boolean vib;
    static int contador=0;
    Mihelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias__alarma);

                setContentView(R.layout.activity_preferencias__alarma);
                borrar=findViewById(R.id.borrar);
                editar=findViewById(R.id.editar);
                db=new Mihelper(getApplicationContext());
                pref=getSharedPreferences("Mispreferencias", Context.MODE_PRIVATE);
                editor=pref.edit();
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
                borrar.setOnClickListener(new View.OnClickListener() {
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
                                if (!Container.alarmas.isEmpty()) {
                                    for (Alarma a : Container.alarmas) {

                                        Intent intent = new Intent(getApplicationContext(), MyAlarmReceiver.class);
                                        PendingIntent sender = PendingIntent.getBroadcast(getApplicationContext(), a.getId_alarma()+1, intent, 0);
                                        PendingIntent sender2 = PendingIntent.getBroadcast(getApplicationContext(), a.getId_alarma()+2, intent, 0);
                                        PendingIntent sender3 = PendingIntent.getBroadcast(getApplicationContext(), a.getId_alarma()+3, intent, 0);
                                        PendingIntent sender4 = PendingIntent.getBroadcast(getApplicationContext(), a.getId_alarma()+4, intent, 0);
                                        PendingIntent sender5 = PendingIntent.getBroadcast(getApplicationContext(), a.getId_alarma()+5, intent, 0);
                                        PendingIntent sender6 = PendingIntent.getBroadcast(getApplicationContext(), a.getId_alarma()+6, intent, 0);
                                        PendingIntent sender7 = PendingIntent.getBroadcast(getApplicationContext(), a.getId_alarma()+7, intent, 0);


                                        AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                                        alarmManager.cancel(sender);
                                        alarmManager.cancel(sender2);
                                        alarmManager.cancel(sender3);
                                        alarmManager.cancel(sender4);
                                        alarmManager.cancel(sender5);
                                        alarmManager.cancel(sender6);
                                        alarmManager.cancel(sender7);


                                    }
                                    db.eliminarTodaslasAlarmas();
                                    ListViewInflater.listAlarmas.clear();
                                    Menu_Alarma.listView.invalidateViews();
                                    Toast.makeText(getApplicationContext(), "Alarmas borradas", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(getApplicationContext(), "No existen alarmas", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                        builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setIcon(R.drawable.alarma_copy);
                        builder.show();

                    }
                });

                editar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent go=new Intent(getApplicationContext(),Pasos_Despertar.class);
                        startActivity(go);
                        }
                });

            }
    @Override
    public void onBackPressed() {

                        Intent go = new Intent(getApplicationContext(),Menu_Alarma.class);
                       startActivity(go);

    }
        }