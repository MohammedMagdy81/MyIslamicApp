package com.example.islamy.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.islamy.model.quran.SurahDetails;
import com.example.islamy.model.quranresponse.SurahDetailsResponse;
import com.example.islamy.repository.SurahDetailsRepo;

import java.util.List;

public class SurahDetailsViewModel extends ViewModel {
    public SurahDetailsRepo surahDetailsRepo;

    public SurahDetailsViewModel() {
        surahDetailsRepo = new SurahDetailsRepo();
    }

    public LiveData<SurahDetailsResponse> surahsDetailsData(String lang, int soraId) {
        return surahDetailsRepo.getSurahDetails(lang, soraId);
    }
    public String timeToMilliSecond(long time) {
        String timerString = "";
        String secondString = "";

        int hour = (int) (time / (1000 * 60 * 60));
        int minutes = (int) (time % (1000 * 60 * 60)) / (1000 * 60);
        int second = (int) ((time % (1000 * 60 * 60)) % (1000 * 60) / 1000);

        if (hour > 0) {
            timerString = hour + ":";
        }
        if (second < 10) {
            secondString = "0" + second;
        } else {
            secondString = second + "";
        }
        timerString = timerString + minutes + ":" + secondString;
        return timerString;
    }
}
