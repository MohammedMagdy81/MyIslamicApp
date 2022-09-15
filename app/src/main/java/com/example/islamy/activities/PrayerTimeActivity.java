package com.example.islamy.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.islamy.R;
import com.example.islamy.adapter.prayerTime.PrayerTimeAdapter;
import com.example.islamy.databinding.ActivityPrayerTimeBinding;
import com.example.islamy.model.prayertime.PrayerResponse;
import com.example.islamy.model.prayertime.MyTimings;
import com.example.islamy.model.prayertime.Timings;
import com.example.islamy.notification.AzanUtils;
import com.example.islamy.notification.RegisterPrayersTimeWorker;
import com.example.islamy.viewmodels.PrayerTimeViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrayerTimeActivity extends AppCompatActivity {

    private PrayerTimeViewModel viewModel;
    private PrayerTimeAdapter adapter;
    private ActivityPrayerTimeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPrayerTimeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Calendar calendar = Calendar.getInstance();
        binding.bottomNavigation.setSelectedItemId(R.id.menu_prayers);
        viewModel = new ViewModelProvider(this).get(PrayerTimeViewModel.class);

        setUpAdapter();
        setUpNavigation();
        setUpCalender();
        observeToLiveData();
        viewModel.observeToPrayers(this,calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.YEAR));

        AzanUtils.registerPrayerTimeWorker(getApplicationContext());


    }



    private void observeToLiveData() {
        viewModel.getMyTimingsLiveData().observe(this, myTimings -> {
            adapter.setMyTimings(myTimings);
        });

        viewModel.showProgress.observe(this, aBoolean -> {
            if (aBoolean) {
                binding.progressBarPrayers.setVisibility(View.VISIBLE);
            } else {
                binding.progressBarPrayers.setVisibility(View.GONE);
            }
        });

        viewModel.messageLiveData.observe(this, this::showDialog);

    }

    private void showDialog(String message) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage(message);
        dialog.setCancelable(true);
        dialog.show();
    }

    private void setUpCalender() {
        Calendar calendar = Calendar.getInstance();
        binding.dataPicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), (datePicker, year, month, day) -> {
                    viewModel.observeToPrayers(this,day, month , year);
                });
    }

    private void setUpAdapter() {
        adapter = new PrayerTimeAdapter();
        binding.prayersTimeRv.setHasFixedSize(true);
        binding.prayersTimeRv.setAdapter(adapter);
    }

    private void setUpNavigation() {
        binding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_azkar:
                    startActivity(new Intent(this, AzkarActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;

                case R.id.menu_radio:
                    startActivity(new Intent(this, RadioActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;

                case R.id.menu_ahadeth:
                    startActivity(new Intent(this, AhadethListActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;

                case R.id.menu_quran:
                    startActivity(new Intent(this, QuranActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;

                case R.id.menu_prayers:
                    return true;
            }
            return false;
        });
    }

}