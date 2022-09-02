package com.example.islamy.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.islamy.Constant;
import com.example.islamy.R;
import com.example.islamy.adapter.ahadeth.AhadethAdapter;
import com.example.islamy.databinding.ActivityAhadethBinding;
import com.example.islamy.model.ahadeth.AhadethProvider;
import com.example.islamy.model.ahadeth.Hadith;

import java.util.ArrayList;
import java.util.List;

public class AhadethActivity extends AppCompatActivity {
    private ActivityAhadethBinding binding;
    private AhadethAdapter adapter;
    List<Hadith> ahadeeth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityAhadethBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        int position= getIntent().getIntExtra(Constant.HADEETH_POSITION,0);
        setUpAdapter(position);

    }

    private void setUpAdapter(int position) {
        ahadeeth= AhadethProvider.getAhadeeth(position);
        adapter= new AhadethAdapter((ArrayList<Hadith>) ahadeeth);
        binding.ahadeethRv.setHasFixedSize(true);
        binding.ahadeethRv.setAdapter(adapter);
    }
}