package com.example.islamy.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.islamy.model.quranresponse.SurahDetailsResponse;
import com.example.islamy.repository.SurahDetailsRepo;

public class SurahDetailsViewModel extends ViewModel {
    public SurahDetailsRepo surahDetailsRepo;

    public SurahDetailsViewModel(){
        surahDetailsRepo=new SurahDetailsRepo();
    }

    public LiveData<SurahDetailsResponse> surahsDetailsData(String lang, int soraId){
        return surahDetailsRepo.getSurahDetails(lang,soraId);
    }
}
