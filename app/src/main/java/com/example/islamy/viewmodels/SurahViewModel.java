package com.example.islamy.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.islamy.model.quran.Surah;
import com.example.islamy.model.quranresponse.SurahResponse;
import com.example.islamy.repository.SurahRepo;

import java.util.ArrayList;

public class SurahViewModel extends ViewModel {

    private SurahRepo surahRepo;


    public SurahViewModel() {
        surahRepo= new SurahRepo();

    }

    public LiveData<SurahResponse> getSurah(){
        return surahRepo.getSurah();
    }

    public void fillList(SurahResponse surahResponse, ArrayList<Surah> surahs){
        for (int i =0 ;i<surahResponse.getData().size();i++){
            surahs.add(new Surah(
                            surahResponse.getData().get(i).getNumber(),
                            String.valueOf(surahResponse.getData().get(i).getEnglishName()),
                            surahResponse.getData().get(i).getNumberOfAyahs(),
                            String.valueOf(surahResponse.getData().get(i).getRevelationType()),
                            String.valueOf(surahResponse.getData().get(i).getName()),
                            String.valueOf(surahResponse.getData().get(i).getEnglishNameTranslation())
                    )
            );
        }
    }


}
