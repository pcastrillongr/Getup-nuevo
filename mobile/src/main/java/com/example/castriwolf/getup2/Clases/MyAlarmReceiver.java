package com.example.castriwolf.getup2.Clases;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.castriwolf.getup2.Menu_Alarma;
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