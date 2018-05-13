package com.example.castriwolf.getup2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);
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
        recogerDatos();

        if(minuto<10) {
            txtHora.setText(hora + ":0" + minuto);
        }
        else{
            txtHora.setText(hora + ":" + minuto);
        }
        txtLevantarse.setText(Tlevantarse+" Min");
        txtBano.setText(Tbano+" Min");
        txtDesayuno.setText(Tdesayuno+" Min");
        txtOtros.setText(Totros+" Min");

        comprobarDatos();

    }

    private void comprobarDatos() {

        if(lunes==true){

            imgLunes.setImageResource(R.drawable.icons8lunes40verde);
        }
        if(martes==true){

            imgMartes.setImageResource(R.drawable.icons8martes40verde);
        }
        if(miercoles==true){

            imgMiercoles.setImageResource(R.drawable.icons8miercoles40verde);
        }
        if(jueves==true){

            imgJueves.setImageResource(R.drawable.icons8jueves40verde);
        }
        if(viernes==true){

            imgViernes.setImageResource(R.drawable.icons8viernes40verde);
        }
        if(sabado==true){

            imgSabado.setImageResource(R.drawable.icons8sabado40verde);
        }
        if(domingo==true){

            imgDomingo.setImageResource(R.drawable.icons8domingo40verde);
        }
        if(coche == true){
            imgModo.setImageResource(R.drawable.icons8cocheverde);
        }
        if(bus == true){
            imgModo.setImageResource(R.drawable.icons8autobusverde);
        }
        if(bici == true){
            imgModo.setImageResource(R.drawable.icons8biciverde);
        }
        if(andar == true){
            imgModo.setImageResource(R.drawable.icons8caminarverde);
        }


    }

    private void recogerDatos(){
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
}
