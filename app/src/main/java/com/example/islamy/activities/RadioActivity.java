package com.example.islamy.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import com.example.islamy.R;
import com.example.islamy.adapter.radio.RadioAdapter;
import com.example.islamy.databinding.ActivityRadioBinding;
import com.example.islamy.viewmodels.RadioViewModel;

import java.io.IOException;

public class RadioActivity extends AppCompatActivity {
    private ActivityRadioBinding binding;
    private RadioViewModel viewModel;
    private RadioAdapter adapter;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityRadioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel= new ViewModelProvider(this).get(RadioViewModel.class);
        binding.bottomNavigation.setSelectedItemId(R.id.menu_radio);
        setUpMediaPlayer();
        setUpNavigation();
        setUpAdapter();
        observeOnData();
        initClickListener();
    }

    private void initClickListener() {
        adapter.onPlayItemClick= (position, item) -> {
            playRadio(item.getRadioUrl());
        };
        adapter.onStopItemClick= (position, item) -> {
            stopRadio();
        };
    }
    private void playRadio(String radioUrl){
        stopRadio();
        try {
            mediaPlayer.setDataSource(radioUrl);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(MediaPlayer::start);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void stopRadio(){
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
        mediaPlayer.reset();
    }


    private void observeOnData() {
        viewModel.getRadioData().observe(this, radioItems -> {
            adapter.setChannels(radioItems);
        });
        viewModel.showProgressState().observe(this, aBoolean -> {
            if (aBoolean){
                binding.progress.setVisibility(View.GONE);
            }
        });
    }

    private void setUpAdapter() {
        binding.channelsRv.setHasFixedSize(true);
        adapter=new RadioAdapter();
        binding.channelsRv .setAdapter(adapter);
    }

    private void setUpMediaPlayer() {
        mediaPlayer= new MediaPlayer();
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

                case R.id.menu_quran:
                    startActivity(new Intent(this,QuranActivity.class));
                    overridePendingTransition(0,0);
                    finish();
                    return true;
                case R.id.menu_ahadeth:
                    startActivity(new Intent(this, AhadethListActivity.class));
                    overridePendingTransition(0,0);
                    finish();
                    return true;
                case R.id.menu_radio:
                    return true;
            }
            return false;
        });
    }





}