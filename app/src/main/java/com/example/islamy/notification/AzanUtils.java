package com.example.islamy.notification;

import android.content.Context;

import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

public class AzanUtils {

    public static void registerPrayerTimeWorker(Context context) {
        WorkManager.getInstance(context.getApplicationContext()).cancelAllWork();

        PeriodicWorkRequest mRegisterRequest = new PeriodicWorkRequest.Builder(RegisterPrayersTimeWorker.class,
                30,
                TimeUnit.DAYS)
                .addTag("REGISTER_PRAYERS")
                .build();
        WorkManager.getInstance(context.getApplicationContext()).enqueueUniquePeriodicWork("REGISTER_PRAYERS",
                ExistingPeriodicWorkPolicy.REPLACE, mRegisterRequest);
    }
}
