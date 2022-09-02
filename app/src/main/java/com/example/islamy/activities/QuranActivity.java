package com.example.islamy.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.islamy.Constant;
import com.example.islamy.R;
import com.example.islamy.adapter.quran.SurahAdapter;
import com.example.islamy.databinding.ActivityMainBinding;
import com.example.islamy.model.quran.Surah;
import com.example.islamy.viewmodels.SurahViewModel;

import java.util.ArrayList;

public class QuranActivity extends AppCompatActivity implements SurahAdapter.OnSurahListener {

    private ActivityMainBinding binding;
    private SurahAdapter surahAdapter;
    private SurahViewModel surahViewModel;
    private ArrayList<Surah> surahs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        surahViewModel= new ViewModelProvider(this).get(SurahViewModel.class);
        setUpRecyclerView();
        setUpAdapter();
        binding.bottomNavigation.setSelectedItemId(R.id.menu_quran);
        observeLiveData();
        setUpNavigation();


    }

    private void setUpNavigation() {
        binding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            switch(item.getItemId())
            {
                case R.id.menu_azkar:
                    startActivity(new Intent(this,AzkarActivity.class));
                    overridePendingTransition(0,0);
                    finish();
                    return true;

                case R.id.menu_radio:
                    startActivity(new Intent(this,RadioActivity.class));
                    overridePendingTransition(0,0);
                    finish();
                    return true;

                case R.id.menu_ahadeth:
                    startActivity(new Intent(this, AhadethListActivity.class));
                    overridePendingTransition(0,0);
                    finish();
                    return true;


                case R.id.menu_quran:
                    return true;
            }
            return false;
        });
    }


    private void observeLiveData() {
        surahViewModel.getSurah().observe(this, surahResponse -> {
        surahViewModel.fillList(surahResponse,surahs);
        surahAdapter.changeData(surahResponse.getData());
        });
    }

    private void setUpAdapter() {
        surahAdapter=new SurahAdapter(this,new ArrayList<>(),this);
        binding.surahRV.setAdapter(surahAdapter);
    }

    private void setUpRecyclerView() {
        binding.surahRV.setLayoutManager(new LinearLayoutManager(this));
        binding.surahRV.setHasFixedSize(true);
        surahs=new ArrayList<>();
    }

    @Override
    public void onSurahListener(int position) {
       sendData(position);
    }

    private void sendData(int position) {
        Intent intent= new Intent(QuranActivity.this, SurahDetailsActivity.class);
        intent.putExtra(Constant.SORA_NO,surahs.get(position).getNumber());
        intent.putExtra(Constant.SORA_TOTAL_AYA,surahs.get(position).getNumberOfAyahs());
        intent.putExtra(Constant.SORA_NAME,surahs.get(position).getName());
        intent.putExtra(Constant.SORA_TYPE,surahs.get(position).getRevelationType());
        intent.putExtra(Constant.SORA_TRANSLATION,surahs.get(position).getEnglishNameTranslation());
        startActivity(intent);
    }

}