package com.example.castriwolf.getup2.Clases;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.castriwolf.getup2.R;

/**
 * Created by cristinavilas on 5/14/18.
 */

public class MyAlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent go=new Intent(context,MyNewIntentService.class);
        context.startService(go);


    }
}