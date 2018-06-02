package com.example.castriwolf.getup2.Clases;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.castriwolf.getup2.Menu_Alarma;
import com.example.castriwolf.getup2.PararAlarma;
import com.example.castriwolf.getup2.R;

import java.util.Calendar;

/**
 * Created by cristinavilas on 5/17/18.
 */

public class MyNewIntentService extends IntentService {
    private static final int NOTIFICATION_ID = 3;
    private Notification notificationCompat;
    private static NotificationManager managerCompat;
    static boolean sonar = true;
    static MediaPlayer player;


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
         player = MediaPlayer.create(this, alarmUri);
        player.setLooping(true);
        player.start();


        //Intent go = new Intent(this, PararAlarma.class);
        //startActivity(go);
        Intent go = new Intent(getApplicationContext(), StepCounter.class);
        go.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(go);



    }

    public static void cancelar() {
        player.stop();

    }

    public static void posponer() {
        player.stop();


    }
}
