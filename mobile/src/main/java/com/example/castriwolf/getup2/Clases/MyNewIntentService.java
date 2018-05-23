package com.example.castriwolf.getup2.Clases;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

import com.example.castriwolf.getup2.PararAlarma;

/**
 * Created by cristinavilas on 5/17/18.
 */

public class MyNewIntentService extends IntentService {
    private static final int NOTIFICATION_ID = 3;
    private Notification notificationCompat;
    private static NotificationManager managerCompat;
    private static Ringtone ringtone;



    public MyNewIntentService() {
        super("MyNewIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        boolean sonar = true;
        Uri alarmUri = RingtoneManager
                .getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager
                    .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        ringtone = RingtoneManager.getRingtone(getApplicationContext(), alarmUri);
       // ringtone.play();


        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("Alarma Sonando");
        builder.setContentText("Te quedan "+String.valueOf(30-Container.pasos)+" para que la alarma se apague");
        builder.setSmallIcon(android.support.v4.R.drawable.notification_icon_background);

        Intent notifyIntent = new Intent(this, PararAlarma.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 2, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        //to be able to launch your activity from the notification
        builder.setContentIntent(pendingIntent);
        notificationCompat = builder.build();
        managerCompat = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(NOTIFICATION_ID, notificationCompat);


    }

    public static void cancelar() {
        ringtone.stop();

    }

    public static void posponer(){
        ringtone.stop();

    }
}
