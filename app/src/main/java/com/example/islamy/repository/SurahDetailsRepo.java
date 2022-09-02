package com.example.islamy.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.islamy.model.quranresponse.SurahDetailsResponse;
import com.example.islamy.network.ApiClient;
import com.example.islamy.network.JsonPlaceHolderApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SurahDetailsRepo {

    public JsonPlaceHolderApi jsonPlaceHolderApi;
    MutableLiveData<SurahDetailsResponse> surahsDetailsData;

    public SurahDetailsRepo(){
        jsonPlaceHolderApi= ApiClient.getInstance().create(JsonPlaceHolderApi.class);
        surahsDetailsData = new MutableLiveData<>();
    }

    public LiveData<SurahDetailsResponse> getSurahDetails(String lang, int soraId){
        jsonPlaceHolderApi.getSurahDetails(lang,soraId).enqueue(new Callback<SurahDetailsResponse>() {
            @Override
            public void onResponse(Call<SurahDetailsResponse> call, Response<SurahDetailsResponse> response) {
                if (response.isSuccessful()){
                    surahsDetailsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<SurahDetailsResponse> call, Throwable t) {

            }
        });
      return surahsDetailsData;
    }
}
