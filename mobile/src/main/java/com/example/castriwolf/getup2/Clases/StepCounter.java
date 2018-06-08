package com.example.castriwolf.getup2.Clases;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.castriwolf.getup2.Menu_Alarma;
import com.example.castriwolf.getup2.R;

public class StepCounter extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensor;
    private boolean andando=false;
    private TextView tPasos;
    private int pasos;
    private int totalpasos;
    SharedPreferences pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_counter);
        pref= getSharedPreferences("MisPreferencias", MODE_PRIVATE);
        totalpasos=pref.getInt("pasos",30);

        sensor=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        tPasos = findViewById(R.id.pasitos);
        pasos=0;
        tPasos.setText("Te quedan "+String.valueOf(totalpasos)+" pasos para que la alarma se pare,\nGetUp!");


    }

    @Override
    protected void onResume()
    {
        super.onResume();
        andando=true;
        Sensor counteSensor=sensor.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(counteSensor!=null)
        {
            sensor.registerListener(this,counteSensor,SensorManager.SENSOR_DELAY_UI);
        }else{
            Toast.makeText(this,"Sensor no encontrado", Toast.LENGTH_LONG);
        }

    }

    @Override

    protected  void onPause(){
        super.onPause();
        andando=false;
    }

    @Override
    protected void onStop(){
        super.onStop();

    }
    @Override
    public void onSensorChanged(SensorEvent event) {

        if(pasos>=totalpasos){
            MyNewIntentService.cancelar();
            event.values[0]=0;
            pasos=0;

            finish();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);



        }else {

            event.values[0] = pasos;
            pasos += 1;
            tPasos.setText("Te quedan " + String.valueOf(totalpasos - (int)event.values[0]) + " pasos para que la alarma se pare,\nGetUp!");
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}
