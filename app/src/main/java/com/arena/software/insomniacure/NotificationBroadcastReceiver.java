package com.arena.software.insomniacure;

import 	android.content.BroadcastReceiver;
import android.widget.Toast;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.app.NotificationManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.media.Ringtone;
import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * Created by Muc on 1/26/2017.
 */

//http://stackoverflow.com/questions/8766739/show-an-alert-dialog-in-broadcast-receiver-after-a-system-reboot

public class NotificationBroadcastReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Don't panik but your time is up!!!!.",
                Toast.LENGTH_LONG).show();
        // Vibrate the mobile phone
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);
/*
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Look at this dialog!")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();*/

/*
        //Define Notification Manager
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

//Define sound URI
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_info_black_24dp)
                .setContentTitle("TEST")
                .setContentText("TEST MSG")
                .setSound(soundUri); //This sets the sound to play

//Display notification
        notificationManager.notify(0, mBuilder.build());

       /* Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        Ringtone r = RingtoneManager.getRingtone(context, notification);
        r.play();*/
    }
}
