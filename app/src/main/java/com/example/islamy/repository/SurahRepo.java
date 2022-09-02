package com.example.islamy.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.islamy.model.quranresponse.SurahResponse;
import com.example.islamy.network.ApiClient;
import com.example.islamy.network.JsonPlaceHolderApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SurahRepo {

    public JsonPlaceHolderApi jsonPlaceHolderApi;
    public MutableLiveData<SurahResponse> responseMutableLiveData;

    public MutableLiveData<String> message;

    public SurahRepo(){
        jsonPlaceHolderApi= ApiClient.getRetrofit().create(JsonPlaceHolderApi.class);
    }

    public LiveData<SurahResponse> getSurah(){
        responseMutableLiveData= new MutableLiveData<>();
        jsonPlaceHolderApi.getSurah().enqueue(new Callback<SurahResponse>() {
            @Override
            public void onResponse(Call<SurahResponse> call, Response<SurahResponse> response) {
                if (response.isSuccessful()){
                    responseMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<SurahResponse> call, Throwable t) {
                    message.setValue(t.getLocalizedMessage());
            }
        });
        return responseMutableLiveData;
    }


}
