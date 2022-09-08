package com.example.islamy.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.islamy.Constant;
import com.example.islamy.R;
import com.example.islamy.activities.PrayerTimeActivity;

public class AzanNotification  extends Worker {

    public AzanNotification(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    public void sendNotification(String title, String body, Uri sound) {
        NotificationManager notificationManager =
                (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder notification = creteNotification(title,body,sound);
        createNotificationChannel(notificationManager,sound);

        notificationManager.notify(Constant.NOTIFICATION_ID, notification.build());
    }

    private NotificationCompat.Builder creteNotification(String title, String body, Uri sound) {

        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(), Constant.CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.drawable.azan_icon)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSound(sound);
        return notification;
    }

    private void createNotificationChannel(NotificationManager notificationManager,Uri sound) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel = new NotificationChannel(
                    Constant.CHANNEL_ID,
                    Constant.CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH);

            AudioAttributes attributes= new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            notificationChannel.setSound(sound,attributes);
            notificationManager.createNotificationChannel(notificationChannel);
    }


}

    @NonNull
    @Override
    public Result doWork() {
        Data input= getInputData();

        String title=input.getString(Constant.NOTIFICATION_TITLE);
        String body=input.getString(Constant.NOTIFICATION_BODY);
        Uri sound= Uri.parse("android.resource://"+getApplicationContext().getPackageName()+ "/" +R.raw.azan);

        sendNotification(title,body,sound);
        return Result.success();
    }



}
