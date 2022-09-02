package com.example.islamy.adapter.ahadeth;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.islamy.R;
import com.example.islamy.model.ahadeth.Hadith;

import java.util.ArrayList;

public class AhadethAdapter extends RecyclerView.Adapter<AhadethAdapter.AhadethViewHolder>{
    ArrayList<Hadith> hadiths;

    public AhadethAdapter(ArrayList<Hadith> hadiths) {
        this.hadiths = hadiths;
    }

    @NonNull
    @Override
    public AhadethViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hadeeth,parent,false);
        return new AhadethViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull AhadethViewHolder holder, int position) {
        holder.bind(hadiths.get(position));
    }

    @Override
    public int getItemCount() {
        return hadiths==null?0:hadiths.size();
    }

    class AhadethViewHolder extends RecyclerView.ViewHolder {
        private TextView hadeethTxt;

        public AhadethViewHolder(@NonNull View itemView) {
            super(itemView);
            hadeethTxt= itemView.findViewById(R.id.hadithText);
        }

        public void bind(Hadith hadith) {
            hadeethTxt.setText(hadith.getHadethName());
        }
    }
}
