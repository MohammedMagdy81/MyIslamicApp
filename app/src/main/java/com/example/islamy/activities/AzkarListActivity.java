package com.example.islamy.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.islamy.Constant;
import com.example.islamy.R;
import com.example.islamy.adapter.azkar.AzkarListAdapter;
import com.example.islamy.databinding.ActivityAzkarListBinding;
import com.example.islamy.viewmodels.AzkarListViewModel;

public class AzkarListActivity extends AppCompatActivity {

    private ActivityAzkarListBinding binding;
    private AzkarListViewModel viewModel;
    private String zekrType;
    private AzkarListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityAzkarListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel= new ViewModelProvider(this).get(AzkarListViewModel.class);
        zekrType= getIntent().getStringExtra(Constant.ZEKR_TYPE);
        setUpAdapter();



    }

    private void setUpAdapter() {
        adapter= new AzkarListAdapter();
        binding.azkarListRecyclerView.setHasFixedSize(true);
        binding.azkarListRecyclerView.setAdapter(adapter);
        adapter.setAzkar(viewModel.getAzkarByType(zekrType));
    }
}