package com.example.islamy.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.islamy.model.radio.RadioItem;
import com.example.islamy.model.radio.RadioResponse;
import com.example.islamy.repository.RadioRepositiry;

import java.util.List;

public class RadioViewModel  extends AndroidViewModel {

    private RadioRepositiry repositiry;


    public RadioViewModel(@NonNull Application application) {
        super(application);
        repositiry= new RadioRepositiry();
    }

    public MutableLiveData<List<RadioItem>> getRadioData(){
        return repositiry.getRadioData();
    }

    public MutableLiveData<Boolean> showProgressState(){
        return repositiry.showProgress;
    }


}
