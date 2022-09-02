package com.example.islamy.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static Retrofit instance;

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

    public static Retrofit getRadioInstance(){
        if (instance !=null){
            instance=null;
        }
        instance=new Retrofit.Builder()
                .baseUrl("http://api.mp3quran.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return instance;
    }

}
