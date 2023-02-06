package com.diya.dementiacare;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.core.app.NotificationCompat;

;

/**
 * Created by dell on 12-01-2018.
 */
public class NotificationManager2 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Uri soundUri = Uri.parse(
                "android.resource://" +
                        context.getApplicationContext().getPackageName() +
                        "/" +
                        R.raw.notify);

        Bundle extras = intent.getExtras();
        String Title = extras.getString("notiTitle");
        String content = extras.getString("notiContent");
        int notid = extras.getInt("notificationID");
        PendingIntent pendingIntent = PendingIntent.getActivity(context, notid, intent, 0);
        String n_id = intent.getStringExtra(context.getString(R.string.n_id));
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //Create channel in which to send push notifications
        String CHANNEL_ID = "my_channel_01";
// only use NotificationChannel when Api Level >= 26
        if (Build.VERSION.SDK_INT >= 26) {
            CharSequence name = "my_channel";
            String Description = "This is my channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(mChannel);
        }
        Log.d("flow",Title);
        Log.d("flow",content);
        //Send push notification
        Notification notif = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentIntent(pendingIntent)
                .setContentTitle(Title)
                .setContentText(content)
                .setSmallIcon(R.drawable.ic_action_alarms)
                .build();
        MediaPlayer mp = MediaPlayer. create (context.getApplicationContext(), soundUri);
        mp.start();
        notif.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(notid, notif);
        notificationManager.cancel(notid-1);
    }

}

