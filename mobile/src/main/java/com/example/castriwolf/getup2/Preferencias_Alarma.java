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
import android.widget.Switch;
import android.widget.Toast;

public class Preferencias_Alarma extends AppCompatActivity {


    Button saliralarma;
    Switch vibracion;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    boolean vib;
    static int contador=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias__alarma);

                setContentView(R.layout.activity_preferencias__alarma);

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
                        builder.setTitle("Salir del GetUp!");
                        builder.setMessage("Estas seguro que deseas salir de GetUp!?");
                        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {


                                finish();
                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.addCategory(Intent.CATEGORY_HOME);
                                startActivity(intent);                    }
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