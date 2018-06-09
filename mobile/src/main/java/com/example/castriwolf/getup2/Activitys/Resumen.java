package com.example.castriwolf.getup2.Activitys;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.castriwolf.getup2.Activitys.Menu_Alarma;
import com.example.castriwolf.getup2.Base_Datos.Mihelper;
import com.example.castriwolf.getup2.Clases.MyAlarmReceiver;
import com.example.castriwolf.getup2.R;

import java.util.ArrayList;
import java.util.Calendar;

public class Resumen extends AppCompatActivity {

    private boolean lunes;
    private boolean martes;
    private boolean miercoles;
    private boolean jueves;
    private boolean viernes;
    private boolean sabado;
    private boolean domingo;
    private int hora;
    private int minuto;
    private int horaRecorrido;
    private int minutosRecorrido;
    private int Tlevantarse;
    private int Tbano;
    private int Tdesayuno;
    private int Totros;
    private Boolean coche;
    private Boolean bus;
    private Boolean bici;
    private Boolean andar;
    private ImageView crear;
    private TextView txtHora;
    private ImageView imgLunes;
    private ImageView imgMartes;
    private ImageView imgMiercoles;
    private ImageView imgJueves;
    private ImageView imgViernes;
    private ImageView imgSabado;
    private ImageView imgDomingo;
    private ImageView imgModo;
    private TextView txtSalida;
    private TextView txtLlegada;
    private TextView txtLevantarse;
    private TextView txtBano;
    private TextView txtDesayuno;
    private TextView txtOtros;
    private TextView txtTotal;
    private TextView txtTrecorrido;
    private double total;
    private int horarestar;
    private int minutosrestar;
    private int horadespertar;
    private int minutosdespertar;
    ArrayList<Integer> diasdelasemana;
    private ImageView cancelar;
    int idalarma;
    private int l,m,x,j,v,s,d; //dias de la semana
    Mihelper db;
    private int minutosTotales;
    private int recorridosEnMinutos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);


        horarestar = 0;
        minutosrestar = 0;
        crear = findViewById(R.id.guardar);
        txtHora = findViewById(R.id.txtHora);
        txtSalida = findViewById(R.id.txtsalida);
        txtLlegada = findViewById(R.id.txtllegada);
        imgLunes = findViewById(R.id.imgLunes);
        imgMartes = findViewById(R.id.imgMartes);
        imgMiercoles = findViewById(R.id.imgMiercoles);
        imgJueves = findViewById(R.id.imgJueves);
        imgViernes = findViewById(R.id.imgViernes);
        imgSabado = findViewById(R.id.imgSabado);
        imgDomingo = findViewById(R.id.imgDomingo);
        imgModo = findViewById(R.id.imgModo);
        txtLevantarse = findViewById(R.id.txtLevantarse);
        txtDesayuno = findViewById(R.id.txtDesayunar);
        txtBano = findViewById(R.id.txtBaño);
        txtOtros = findViewById(R.id.txtOtros);
        txtTotal = findViewById(R.id.txtResultado);
        txtTrecorrido = findViewById(R.id.txtTrecorrido);
        cancelar=findViewById(R.id.cancelaralarma);

        diasdelasemana = new ArrayList<>();
        recogerDatos();

        if (minuto < 10) {
            txtHora.setText(hora + ":0" + minuto);
        } else {
            txtHora.setText(hora + ":" + minuto);
        }
        txtLevantarse.setText(Tlevantarse + " Minutos");
        txtBano.setText(Tbano + " Minutos");
        txtDesayuno.setText(Tdesayuno + " Minutos");
        txtOtros.setText(Totros + " Minutos");
        if (horaRecorrido > 1) {
            txtTrecorrido.setText(horaRecorrido + " Horas " + minutosRecorrido + " Minutos");
            minutosRecorrido += horaRecorrido * 60;
        } else {
            txtTrecorrido.setText(minutosRecorrido + " Minutos");
        }

        total = (Tlevantarse + Tbano + Tdesayuno + Totros + minutosRecorrido);
        txtTotal.setText(total + " Minutos");

        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                establecerAlarma();
            }


        });

        comprobarDatos();
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent go=new Intent(getApplicationContext(),Menu_Alarma.class);
                startActivity(go);
                Toast.makeText(getApplicationContext(),"Alarma Cancelada",Toast.LENGTH_SHORT).show();


            }
        });

    }

    private void insertarActividad() {


       db=new Mihelper(getApplicationContext());
       db.insertarActividad("tiempolevantarse",Tlevantarse);
       db.insertarActividad("tiempobaño",Tbano);
       db.insertarActividad("tiempodesayuno" ,Tdesayuno);
       db.insertarActividad("tiempootros",Totros);
       recorridosEnMinutos = (horaRecorrido*60)+minutosRecorrido;
       db.insertarActividad("tiemporecorrido",recorridosEnMinutos);
    }

    private void comprobarDatos() {

        if (lunes == true) {

            imgLunes.setImageResource(R.drawable.icons8lunes40verde);
            l=1;

        }
        if (martes == true) {

            imgMartes.setImageResource(R.drawable.icons8martes40verde);
            m=1;

        }
        if (miercoles == true) {

            imgMiercoles.setImageResource(R.drawable.icons8miercoles40verde);
            x=1;
        }
        if (jueves == true) {

            imgJueves.setImageResource(R.drawable.icons8jueves40verde);
            j=1;

        }
        if (viernes == true) {

            imgViernes.setImageResource(R.drawable.icons8viernes40verde);
            v=1;

        }
        if (sabado == true) {

            imgSabado.setImageResource(R.drawable.icons8sabado40verde);
            s=1;

        }
        if (domingo == true) {

            imgDomingo.setImageResource(R.drawable.icons8domingo40verde);
            d=1;

        }
        if (coche == true) {
            imgModo.setImageResource(R.drawable.icons8cocheverde);
        }
        if (bus == true) {
            imgModo.setImageResource(R.drawable.icons8autobusverde);
        }
        if (bici == true) {
            imgModo.setImageResource(R.drawable.icons8biciverde);
        }
        if (andar == true) {
            imgModo.setImageResource(R.drawable.icons8caminarverde);
        }


    }

    private void recogerDatos() {
        Bundle parametros = getIntent().getExtras();
        //Dias
        lunes = parametros.getBoolean("Lunes");
        martes = parametros.getBoolean("Martes");
        miercoles = parametros.getBoolean("Miercoles");
        jueves = parametros.getBoolean("Jueves");
        viernes = parametros.getBoolean("Viernes");
        sabado = parametros.getBoolean("Sabado");
        domingo = parametros.getBoolean("Domingo");
        //Hora para llegar a tu destino
        hora = parametros.getInt("Hora");
        minuto = parametros.getInt("HMinuto");
        //Tiempo para el recorrido
        horaRecorrido = parametros.getInt("HorasRecorrido");
        minutosRecorrido = parametros.getInt("MinutosRecorridos");
        //Lugares de salida y llegada
        txtSalida.setText(parametros.getString("Lsalida"));
        txtLlegada.setText(parametros.getString("Lllegada"));
        //Tiempo para levantarte
        Tlevantarse = parametros.getInt("Tlevantarse");
        //Tiempo para el baño
        Tbano = parametros.getInt("Tbaño");
        //Tiempo para el desayuno
        Tdesayuno = parametros.getInt("Tdesayuno");
        //tiempo otros
        Totros = parametros.getInt("Textra");
        //Modo Transporte
        coche = parametros.getBoolean("Coche");
        bus = parametros.getBoolean("Bus");
        bici = parametros.getBoolean("Bici");
        andar = parametros.getBoolean("Andar");


    }

    private void establecerAlarma() {


        formulaCalcularAlarma();


        insertarAlarma();
        alarmanager();

        Intent go = new Intent(getApplicationContext(), Menu_Alarma.class);
        startActivity(go);

    }


    private void formulaCalcularAlarma() {
        double resultado;

        minutosTotales = hora * 60;
        minutosTotales += minuto;
        resultado = minutosTotales - total;

        while (resultado >= 60) {
            resultado -= 60;
            horadespertar += 1;

        }
        if (minutosrestar < 60) {
            minutosdespertar = (int) resultado;
        }
        insertarActividad();


    }

    private void alarmanager() {


        Calendar time = Calendar.getInstance();
            Toast.makeText(this, "Alarma Creada a la/s " + horadespertar + ":" + minutosdespertar, Toast.LENGTH_SHORT).show();



        if (lunes) {
            time.set(Calendar.DAY_OF_WEEK, 2);
        }
        if (martes) {
            time.set(Calendar.DAY_OF_WEEK, 3);

        }
        if (miercoles) {
            time.set(Calendar.DAY_OF_WEEK, 4);

        }
        if (jueves) {
            time.set(Calendar.DAY_OF_WEEK, 5);

        }
        if (viernes) {
            time.set(Calendar.DAY_OF_WEEK, 6);

        }
        if (sabado) {
            time.set(Calendar.DAY_OF_WEEK, 7);

        }
        if (domingo) {
            time.set(Calendar.DAY_OF_WEEK, 1);

        }
        if(time.before(Calendar.getInstance())){//if its in the past increment
            time.add(Calendar.DATE,1);
        }


        AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getApplicationContext(), MyAlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), idalarma, intent, 0);

        time.set(Calendar.HOUR_OF_DAY, horadespertar);
        time.set(Calendar.MINUTE, minutosdespertar);
        time.set(Calendar.SECOND, 0);
        time.set(Calendar.MILLISECOND, 0);
        //time.setTimeInMillis(System.currentTimeMillis());
        alarmMgr.set(AlarmManager.RTC_WAKEUP, time.getTimeInMillis(), pendingIntent);



    }


    private void insertarAlarma() {

        Mihelper db = new Mihelper(this);

        String lugarSalida = txtSalida.toString();
        String lugarLlegada = txtLlegada.toString();


        boolean result = db.insertarAlarma(lugarSalida, lugarLlegada, horadespertar, minutosdespertar, hora, minuto,l , m , x , j , v , s , d);
        if (result == true) {

            idalarma=db.getIDAlarma();

            db.close();

            Toast.makeText(this, "Se ha introducido en la bd", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "NO se ha introducido en la bd", Toast.LENGTH_SHORT).show();

        }

        db.close();
}}
