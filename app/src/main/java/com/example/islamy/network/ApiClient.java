package com.example.islamy.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit instance;


    public static Retrofit getRetrofit(){
        if (instance==null){
            instance=new Retrofit.Builder()
                    .baseUrl("http://api.alquran.cloud/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance;
    }

    public static Retrofit getInstance(){
        if (instance !=null){
            instance=null;
        }
        instance=new Retrofit.Builder()
                .baseUrl("https://quranenc.com/api/v1/translation/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return instance;
    }



    private static Retrofit radioInstance;
    public static Retrofit getRadioInstance(){
        if (radioInstance ==null){
            radioInstance=new Retrofit.Builder()
                    .baseUrl("http://api.mp3quran.net/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return radioInstance;
    }




    private static Retrofit prayerTimeInstance;
    public static Retrofit getPrayerTimeInstance(){
        if (prayerTimeInstance==null){
            prayerTimeInstance= new Retrofit.Builder()
                    .baseUrl("http://api.aladhan.com/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return prayerTimeInstance;
    }

    public static JsonPlaceHolderApi getApi(){
        return getPrayerTimeInstance().create(JsonPlaceHolderApi.class);
    }


}
