package com.example.islamy.notification;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.islamy.Constant;
import com.example.islamy.model.prayertime.DataItem;
import com.example.islamy.model.prayertime.MyTimings;
import com.example.islamy.model.prayertime.PrayerResponse;
import com.example.islamy.model.prayertime.Timings;
import com.example.islamy.network.ApiClient;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Response;

public class RegisterPrayersTimeWorker extends Worker {

    public RegisterPrayersTimeWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            Calendar calendar= Calendar.getInstance();

            int year= calendar.get(Calendar.YEAR);
            int month= calendar.get(Calendar.MONTH)+1;

            // call this api again to register data of prayer time . . .
            Response<PrayerResponse> timesResponse = ApiClient.getApi().getPrayers
                            ("Cairo", "Egypt", 5, month, year)
                    .execute();

            List<DataItem> data = timesResponse.body().getData();// prayers of all month
            for (int i = 0; i < data.size(); i++) {
                DataItem dataItem= data.get(i);

                Timings timings = dataItem.getTimings();
                ArrayList<MyTimings> myTimings = convertFromTimings(timings);

                int day=i+1;
                myTimings.forEach(prayers -> {
                    // put data that sended to Azan Work
                    Data input = new Data.Builder()
                            .putString(Constant.NOTIFICATION_TITLE, prayers.getPrayerName())
                            .putString(Constant.NOTIFICATION_BODY, "حان الان موعد الصلاة ")
                            .build();

                    //set Some Constrains
                    Constraints constraints = new Constraints.Builder()
                            .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                            .setRequiresBatteryNotLow(true)
                            .setRequiresCharging(false)
                            .build();

                    String prayerTag = ""+year+""+month+""+day+ " "+ prayers.getPrayerName();
                    OneTimeWorkRequest mRequest = new OneTimeWorkRequest
                            .Builder(AzanNotification.class)
                            .addTag(prayerTag)
                            .setInitialDelay(calculatePrayersDelay(year,month,day,prayers), TimeUnit.MILLISECONDS)
                            .setInputData(input)
                            .build();

                    // tell work manger to do this request
                    WorkManager.getInstance(getApplicationContext())
                            .enqueueUniqueWork(prayerTag,
                                    ExistingWorkPolicy.REPLACE, mRequest) ;
                });

            }
            return Result.success();
        } catch (IOException e) {
            e.printStackTrace();
            return Result.retry();
        }


    }

    private long calculatePrayersDelay(int year,int month,int day,MyTimings prayers){

        String pattern="yyyy/MM/dd HH:mm";
        DecimalFormat decimalFormat= new DecimalFormat("00");
        String time = prayers.getPrayerTime().split(" ")[0];

        String prayerDate=""+year+"/"+decimalFormat.format(month)+"/"+decimalFormat.format(day)+" "+time;
        SimpleDateFormat formatter= new SimpleDateFormat(pattern);

        try {
            Date date = formatter.parse(prayerDate);
            long currentTime = System.currentTimeMillis();
            return Math.abs(date.getTime()-currentTime);

        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }


    }

    public ArrayList<MyTimings> convertFromTimings(Timings timings) {
        ArrayList<MyTimings> result = new ArrayList<>();
        result.add(new MyTimings(timings.getFajr(), "Fajr"));
        result.add(new MyTimings(timings.getSunrise(), "Sunrise"));
        result.add(new MyTimings(timings.getDhuhr(), "Duhr"));
        result.add(new MyTimings(timings.getAsr(), "Asr"));
        result.add(new MyTimings(timings.getMaghrib(), "Maghreb"));
        result.add(new MyTimings(timings.getIsha(), "Isha"));
        return result;
    }
}
