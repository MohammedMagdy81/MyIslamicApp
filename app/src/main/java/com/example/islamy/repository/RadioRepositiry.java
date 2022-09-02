package com.example.islamy.repository;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.islamy.model.radio.RadioItem;
import com.example.islamy.model.radio.RadioResponse;
import com.example.islamy.network.ApiClient;
import com.example.islamy.network.JsonPlaceHolderApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RadioRepositiry  extends ViewModel {

    public JsonPlaceHolderApi jsonPlaceHolderApi;
    public MutableLiveData<List<RadioItem>> radios;
    public MutableLiveData<Boolean> showProgress;

    public RadioRepositiry() {
        jsonPlaceHolderApi= ApiClient.getRadioInstance().create(JsonPlaceHolderApi.class);
    }

    public MutableLiveData<List<RadioItem>> getRadioData(){
        radios= new MutableLiveData<>();
        showProgress= new MutableLiveData<>();
        jsonPlaceHolderApi.getRadioChannels().enqueue(new Callback<RadioResponse>() {
            @Override
            public void onResponse(Call<RadioResponse> call, Response<RadioResponse> response) {
                showProgress.setValue(true);
                if (response.isSuccessful()){
                    showProgress.setValue(false);
                    radios.setValue(response.body().getRadios());
                }
            }

            @Override
            public void onFailure(Call<RadioResponse> call, Throwable t) {

            }
        });
        return radios;
    }
}
