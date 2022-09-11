package com.example.islamy.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.islamy.Constant;
import com.example.islamy.R;
import com.example.islamy.adapter.quran.SurahDetailsAdapter;
import com.example.islamy.databinding.ActivitySurahDetailsBinding;
import com.example.islamy.model.quran.SurahDetails;
import com.example.islamy.viewmodels.SurahDetailsViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SurahDetailsActivity extends AppCompatActivity {

    private ActivitySurahDetailsBinding binding;
    private SurahDetailsViewModel viewModel;
    private SurahDetailsAdapter adapter;
    private List<SurahDetails> surahs;
    int soraNo;
    private RadioGroup audioGroup;
    private RadioButton audioButton;

    private String qariAB = "abdul_basit_murattal";
    private String qariWD = "abdul_wadood_haneef_rare";
    private String qariAR = "abdurrashid_sufi_shu3ba";
    Handler handler = new Handler();
    private String str, qr;
    MediaPlayer mediaPlayer;


    private String english = "english_saheeh";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySurahDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        soraNo = getIntent().getIntExtra(Constant.SORA_NO, 0);
        viewModel = new ViewModelProvider(this).get(SurahDetailsViewModel.class);

        setUpReceivedData();
        setUpRecyclerView();
        getSurahTranslation(english, soraNo);
        setUpSearch();
        setUpQari();
        listenAudio(qariAB);

    }

    @SuppressLint("ClickableViewAccessibility")
    private void listenAudio(String qari) {

        mediaPlayer = new MediaPlayer();
        binding.seekBar.setMax(100);

        binding.playBtn.setOnClickListener(view -> {
            if (mediaPlayer.isPlaying()) {
                handler.removeCallbacks(updater);
                mediaPlayer.pause();
                binding.playBtn.setImageResource(R.drawable.ic_play);
            } else {
                mediaPlayer.start();
                binding.playBtn.setImageResource(R.drawable.ic_stop);
                updateSeekBar();
            }
        });
        prepareMediaPlayer(qari);
        binding.seekBar.setOnTouchListener((view, motionEvent) -> {
            SeekBar seekBar = (SeekBar) view;
            int playPosition = (mediaPlayer.getDuration() / 100) * seekBar.getProgress();
            mediaPlayer.seekTo(playPosition);
            binding.startTime.setText(timeToMilliSecond(mediaPlayer.getCurrentPosition()));
            return false;
        });
        mediaPlayer.setOnBufferingUpdateListener((mediaPlayer, position) -> {
            binding.seekBar.setSecondaryProgress(position);
        });
        mediaPlayer.setOnCompletionListener(mediaPlayer -> {
            binding.seekBar.setProgress(0);
            binding.playBtn.setImageResource(R.drawable.ic_play);
            binding.startTime.setText("0:00");
            binding.totalTime.setText("0:00");
            mediaPlayer.reset();
            prepareMediaPlayer(qari);
        });
    }

    private void prepareMediaPlayer(String qari) {

        if (soraNo < 10) {
            str = "00" + soraNo;
        } else if (soraNo < 100) {
            str = "0" + soraNo;
        } else {
            str = String.valueOf(soraNo);
        }

        try {
            mediaPlayer.setDataSource("https://download.quranicaudio.com/quran/" + qari + "/" + str.trim() + ".mp3");
            mediaPlayer.prepare();
            binding.totalTime.setText(timeToMilliSecond(mediaPlayer.getDuration()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Runnable updater = () -> {
        updateSeekBar();
        long currentDuration = mediaPlayer.getCurrentPosition();
        binding.startTime.setText(timeToMilliSecond(currentDuration));
    };

    private void updateSeekBar() {
        if (mediaPlayer.isPlaying()) {
            binding.seekBar.setProgress((int) (((float) mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration()) * 100));
            handler.postDelayed(updater, 1000);
        }
    }

    // to Convert Time from Long to String
    private String timeToMilliSecond(long time) {
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

    private void setUpQari() {
        binding.btnSetting.setOnClickListener(v -> {

            BottomSheetDialog sheet = new BottomSheetDialog(SurahDetailsActivity.this, R.style.ModalBottomSheetDialog);
            LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
            View view = inflater.inflate(R.layout.bottom_sheet_layout, findViewById(R.id.sheet_container));

            view.findViewById(R.id.sheet_btn_save).setOnClickListener(view1 -> {
                audioGroup = view.findViewById(R.id.sheet_audio_radio_group);
                int audioId = audioGroup.getCheckedRadioButtonId();
                audioButton = view.findViewById(audioId);

                if (audioId == -1) {
                    Toast.makeText(this, "No Qari Selected !", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Qari Selected", Toast.LENGTH_SHORT).show();
                }

                if (audioButton.getText().toString().equals("عبدالباسط عبدالصمد مرتل")) {
                    qr = qariAB;
                } else if (audioButton.getText().toString().equals("عبد الودود حنيف")) {
                    qr = qariWD;
                } else if (audioButton.getText().toString().equals("عبد الرشيد صوفي- رواية شعبة")) {
                    qr = qariAR;
                }

                mediaPlayer.reset();
                mediaPlayer.release();
                listenAudio(qr);
                sheet.dismiss();

            });
            sheet.setContentView(view);
            sheet.show();

        });
    }

    private void setUpSearch() {
        binding.surahDetailsEditTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
    }

    private void filter(String keyWord) {
        ArrayList<SurahDetails> detailsArrayList = new ArrayList<>();

        for (SurahDetails surahDetails : surahs) {

            if (String.valueOf(surahDetails.getAya()).contains(keyWord)) {
                detailsArrayList.add(surahDetails);
            }
        }
        adapter.filter(detailsArrayList);
    }

    private void getSurahTranslation(String lang, int soraNo) {

        viewModel.surahsDetailsData(lang, soraNo).observe(this, surahDetailsResponse -> {
            for (SurahDetails surahDetails : surahDetailsResponse.getResult()) {
                surahs.add(new SurahDetails(
                        surahDetails.getSura(),
                        surahDetails.getAya(),
                        surahDetails.getTranslation(),
                        surahDetails.getId(),
                        surahDetails.getArabicText(),
                        surahDetails.getFootnotes()
                ));
                adapter = new SurahDetailsAdapter(this, surahs);
                binding.suraDetailsRv.setAdapter(adapter);
            }
        });
    }

    private void setUpRecyclerView() {
        binding.suraDetailsRv.setHasFixedSize(true);
        surahs = new ArrayList<>();

    }


    @SuppressLint("SetTextI18n")
    private void setUpReceivedData() {
        binding.soraName.setText(getIntent().getStringExtra(Constant.SORA_NAME));
        binding.soraType.setText(getIntent().getStringExtra(Constant.SORA_TYPE) + " : " +
                getIntent().getIntExtra(Constant.SORA_TOTAL_AYA, 0) + " Aya");
        binding.soraaTranslation.setText(getIntent().getStringExtra(Constant.SORA_TRANSLATION));
    }

    @Override
    protected void onDestroy() {

        if (mediaPlayer.isPlaying()) {
            handler.removeCallbacks(updater);
            mediaPlayer.pause();
            binding.playBtn.setImageResource(R.drawable.ic_baseline_play);

        }
        super.onDestroy();
    }

    @Override
    protected void onPause() {

        if (mediaPlayer.isPlaying()) {
            handler.removeCallbacks(updater);
            mediaPlayer.pause();
            binding.playBtn.setImageResource(R.drawable.ic_baseline_play);

        }
        super.onPause();
    }
}