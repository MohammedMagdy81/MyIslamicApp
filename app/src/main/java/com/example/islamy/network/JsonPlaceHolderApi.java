package com.example.islamy.network;

import com.example.islamy.model.prayertime.PrayerResponse;
import com.example.islamy.model.quranresponse.SurahDetailsResponse;
import com.example.islamy.model.quranresponse.SurahResponse;
import com.example.islamy.model.radio.RadioResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {

    @GET("surah")
    Call<SurahResponse> getSurah();

    @GET("sura/{language}/{id}")
    Call<SurahDetailsResponse> getSurahDetails(@Path("language") String lang ,
                                                  @Path("id") int soraId);

    @GET("radios/radio_arabic.json")
    Call<RadioResponse> getRadioChannels();



    @GET("calendarByCity")
    Call<PrayerResponse> getPrayers(@Query("city") String city,
                                       @Query("country") String country,
                                       @Query("method") int method,
                                       @Query("month") int month,
                                       @Query("year") int year);
}
