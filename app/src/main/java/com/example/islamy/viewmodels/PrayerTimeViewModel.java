package com.example.islamy.viewmodels;

import android.content.Context;
import android.media.MediaPlayer;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.islamy.model.prayertime.Hijri;
import com.example.islamy.model.prayertime.PrayerResponse;
import com.example.islamy.model.prayertime.MyTimings;
import com.example.islamy.model.prayertime.Timings;
import com.example.islamy.network.ApiClient;
import com.example.islamy.notification.AzanUtils;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrayerTimeViewModel extends ViewModel {

    private MutableLiveData<ArrayList<MyTimings>> myTimingsLiveData;
    public MutableLiveData<Boolean> showProgress;
    public MutableLiveData<String> messageLiveData;



    public PrayerTimeViewModel() {
        myTimingsLiveData = new MutableLiveData<>();
        showProgress = new MutableLiveData<>();
        messageLiveData = new MutableLiveData<>();

    }

    public Call<PrayerResponse> getPrayers(String city,
                                           String country,
                                           int method,
                                           int month,
                                           int year) {
        return ApiClient.getApi().getPrayers(city, country, method, month, year);
    }

    public void observeToPrayers(Context context , int day,
                                 int month,
                                 int year) {
        showProgress.setValue(true);
        getPrayers("Cairo", "Egypt", 5, month+1, year).enqueue(new Callback<PrayerResponse>() {
            @Override
            public void onResponse(Call<PrayerResponse> call, Response<PrayerResponse> response) {
                showProgress.setValue(false);
                Timings timings = response.body().getData().get(day - 1).getTimings();
                ArrayList<MyTimings> myTimings = convertFromTimings(timings);
                myTimingsLiveData.setValue(myTimings);
                AzanUtils.registerPrayerTimeWorker(context.getApplicationContext());
            }


            @Override
            public void onFailure(Call<PrayerResponse> call, Throwable t) {
                messageLiveData.setValue(t.getLocalizedMessage());
            }
        });
    }




    public MutableLiveData<ArrayList<MyTimings>> getMyTimingsLiveData() {
        return myTimingsLiveData;
    }

    public ArrayList<MyTimings> convertFromTimings(Timings timings) {
        ArrayList<MyTimings> result = new ArrayList<>();
        result.add(new MyTimings(timings.getFajr(), "Fajr","الفجر"));
        result.add(new MyTimings(timings.getSunrise(), "Sunrise","شروق الشمس"));
        result.add(new MyTimings(timings.getDhuhr(), "Duhr","الظهر"));
        result.add(new MyTimings(timings.getAsr(), "Asr","العصر"));
        result.add(new MyTimings(timings.getMaghrib(), "Maghreb","المغرب"));
        result.add(new MyTimings(timings.getIsha(), "Isha","العشاء"));
        return result;
    }






}