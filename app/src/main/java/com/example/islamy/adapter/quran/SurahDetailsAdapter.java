package com.example.islamy.adapter.quran;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.islamy.R;
import com.example.islamy.model.quran.SurahDetails;

import java.util.ArrayList;
import java.util.List;

public class SurahDetailsAdapter extends RecyclerView.Adapter<SurahDetailsAdapter.SurahDetailsViewHolder>{

    private Context context;
    private List<SurahDetails> listSurahs;

    public SurahDetailsAdapter(Context context, List<SurahDetails> listSurahs) {
        this.context = context;
        this.listSurahs = listSurahs;
    }

    public void filter(ArrayList<SurahDetails> list){
        this.listSurahs=list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SurahDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_sura_details,parent,false);
        return new SurahDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SurahDetailsViewHolder holder, int position) {
            holder.bind(listSurahs.get(position));
    }

    @Override
    public int getItemCount() {
        return listSurahs==null?0:listSurahs.size();
    }

    class SurahDetailsViewHolder extends RecyclerView.ViewHolder {
        private final TextView ayaNo,arabicName,translation;
        public SurahDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            ayaNo=itemView.findViewById(R.id.aya_no);
            arabicName=itemView.findViewById(R.id.aya_arabic_text);
            translation=itemView.findViewById(R.id.aya_translation);
        }


        public void bind(SurahDetails surahDetails) {
            ayaNo.setText(String.valueOf(surahDetails.getAya() ));
            arabicName.setText(surahDetails.getArabicText());
            translation.setText(surahDetails.getTranslation());
        }
    }
}
