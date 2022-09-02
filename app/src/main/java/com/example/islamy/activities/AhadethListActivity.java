package com.example.islamy.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.islamy.Constant;
import com.example.islamy.R;
import com.example.islamy.adapter.ahadeth.AhadethListAdapter;
import com.example.islamy.databinding.ActivityAhadethBinding;
import com.example.islamy.databinding.ActivityAhadethListBinding;
import com.example.islamy.model.ahadeth.AhadeethList;
import com.example.islamy.model.ahadeth.AhadeethListProvider;

import java.util.ArrayList;
import java.util.List;

public class AhadethListActivity extends AppCompatActivity implements AhadethListAdapter.OnHadeethClickListener{

    private ActivityAhadethListBinding binding;
    private AhadethListAdapter adapter;
    List<AhadeethList> ahadeethList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAhadethListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.bottomNavigation.setSelectedItemId(R.id.menu_ahadeth);
        setUpAdapter();
        setUpNavigation();

    }


    private void setUpAdapter() {
        ahadeethList= AhadeethListProvider.getAhadeethList();
        adapter= new AhadethListAdapter(ahadeethList,this);
        binding.ahadeethListRv.setHasFixedSize(true);
        binding.ahadeethListRv.setAdapter(adapter);
    }

    @Override
    public void onHadeethClick(int position) {
        Intent intent= new Intent(AhadethListActivity.this,AhadethActivity.class);
        intent.putExtra(Constant.HADEETH_POSITION,position);
        startActivity(intent);
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
                    return true;
                case R.id.menu_radio:
                    startActivity(new Intent(this,RadioActivity.class));
                    overridePendingTransition(0,0);
                    finish();
                    return true;
            }
            return false;
        });
    }

}