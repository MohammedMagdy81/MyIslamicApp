package com.example.islamy.viewmodels;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModel;

import com.example.islamy.model.azkar.AzkarProvider;
import com.example.islamy.model.azkar.ZekrType;

import java.util.HashSet;

public class AzkarTypeViewModel extends ViewModel {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public HashSet<ZekrType> getAzkarTypes(Context context){
        return AzkarProvider.getAzkarTypes(context);
    }
}
