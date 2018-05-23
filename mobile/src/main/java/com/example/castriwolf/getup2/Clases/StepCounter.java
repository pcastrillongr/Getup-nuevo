package com.example.castriwolf.getup2.Clases;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class StepCounter extends AppCompatActivity implements SensorEventListener {

    SensorManager sensor;
    boolean andando=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sensor=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
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
    public void onSensorChanged(SensorEvent event) {

        if(andando)
        {

            Container.pasos=(int)event.values[0];

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
