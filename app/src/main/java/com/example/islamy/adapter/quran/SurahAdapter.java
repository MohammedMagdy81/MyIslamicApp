package com.example.islamy.adapter.quran;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.islamy.R;
import com.example.islamy.model.quran.Surah;

import java.util.List;

public class SurahAdapter extends RecyclerView.Adapter<SurahAdapter.SurahViewHolder> {

    Context context;
    List<Surah> surahList;
    OnSurahListener onSurahListener;

    public SurahAdapter(Context context, List<Surah> surahList,OnSurahListener onSurahListener) {
        this.context = context;
        this.surahList=surahList;
        this.onSurahListener=onSurahListener;
    }


    public void changeData(List<Surah> surahList){
        this.surahList=surahList;
        notifyDataSetChanged();
    }


    public interface OnSurahListener{
        void onSurahListener(int position);

    }

    @NonNull
    @Override
    public SurahViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_surah,parent,false);
        return new SurahViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SurahViewHolder holder, int position) {
        Surah surah= surahList.get(position);
        holder.bind(surah);

        if (onSurahListener!=null){
            holder.itemView.setOnClickListener(view -> {
                onSurahListener.onSurahListener(position);
            });
        }
    }
    @Override
    public int getItemCount() {
        return surahList.size();
    }

    class SurahViewHolder extends RecyclerView.ViewHolder {
        private  TextView arabicName,englishName,totalAya,suraNumber;

        public SurahViewHolder(@NonNull View itemView) {
            super(itemView);
            arabicName=itemView.findViewById(R.id.arabic_name);
            englishName=itemView.findViewById(R.id.english_name);
            totalAya=itemView.findViewById(R.id.total_aya);
            suraNumber=itemView.findViewById(R.id.surah_number);
        }

        public void bind(Surah surah) {
            englishName.setText(surah.getEnglishName());
            arabicName.setText(surah.getName());
            totalAya.setText("Aya : "+surah.getNumberOfAyahs());
            suraNumber.setText(""+surah.getNumber());
        }
    }
}
