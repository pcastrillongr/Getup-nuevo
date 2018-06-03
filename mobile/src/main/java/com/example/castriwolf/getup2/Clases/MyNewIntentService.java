package com.example.castriwolf.getup2.Clases;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;

/**
 * Created by cristinavilas on 5/17/18.
 */

public class MyNewIntentService extends IntentService {
    private static final int NOTIFICATION_ID = 3;
    private Notification notificationCompat;
    private static NotificationManager managerCompat;
    static boolean sonar = true;
    static MediaPlayer player;
    Vibrator vibrator;
    SharedPreferences pref;
    boolean vibrar;


    public MyNewIntentService() {
        super("MyNewIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

         pref= getSharedPreferences("Mispreferencias", MODE_PRIVATE);
         vibrar=pref.getBoolean("vibracion",false);

        boolean sonar = true;
        Uri alarmUri = RingtoneManager
                .getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager
                    .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
         vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
         player = MediaPlayer.create(this, alarmUri);
        player.setLooping(true);
        player.start();


        if(vibrar)
        {
            while(player.isPlaying()) {
                vibrator.vibrate(300);        }
        }




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
