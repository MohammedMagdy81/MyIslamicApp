package com.example.islamy.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.islamy.model.azkar.AzkarProvider;
import com.example.islamy.model.azkar.Zekr;

import java.util.ArrayList;

public class AzkarListViewModel extends AndroidViewModel {

    public AzkarListViewModel(@NonNull Application application) {
        super(application);
    }

    public ArrayList<Zekr> getAzkarByType(String zekrType){
        return AzkarProvider.getAllAzkarByType(getApplication(),zekrType);
    }
}
