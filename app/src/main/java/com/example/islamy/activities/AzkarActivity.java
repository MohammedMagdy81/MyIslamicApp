package com.example.islamy.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.example.islamy.Constant;
import com.example.islamy.R;
import com.example.islamy.adapter.azkar.AzkarTypeAdapter;
import com.example.islamy.databinding.ActivityAzkarBinding;
import com.example.islamy.viewmodels.AzkarTypeViewModel;

public class AzkarActivity extends AppCompatActivity implements AzkarTypeAdapter.OnZekrTypeClickListener {

    private ActivityAzkarBinding binding;
    private AzkarTypeAdapter adapter;
    AzkarTypeViewModel viewModel;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAzkarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(AzkarTypeViewModel.class);
        setUpAdapter();
        binding.bottomNavigation.setSelectedItemId(R.id.menu_azkar);
        setUpNavigation();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setUpAdapter() {
        adapter = new AzkarTypeAdapter(this);
        binding.azkarTypeRv.setHasFixedSize(true);
        binding.azkarTypeRv.setAdapter(adapter);
        adapter.changeData(viewModel.getAzkarTypes(this));
    }


    private void setUpNavigation() {
        binding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_azkar:
                    return true;
                case R.id.menu_quran:
                    startActivity(new Intent(this, QuranActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;
                case R.id.menu_radio:
                    startActivity(new Intent(this, RadioActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;

                case R.id.menu_prayers:
                    startActivity(new Intent(this, PrayerTimeActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;

                case R.id.menu_ahadeth:
                    startActivity(new Intent(this, AhadethListActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;
            }
            return false;
        });
    }

    @Override
    public void onZekrTypeClick(String zekrType) {
        Intent intent = new Intent(this, AzkarListActivity.class);
        intent.putExtra(Constant.ZEKR_TYPE, zekrType);
        startActivity(intent);
    }
}