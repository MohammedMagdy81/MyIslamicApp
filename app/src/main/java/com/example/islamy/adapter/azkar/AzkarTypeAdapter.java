package com.example.islamy.adapter.azkar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.islamy.R;
import com.example.islamy.model.azkar.ZekrType;

import java.util.ArrayList;
import java.util.HashSet;

public class AzkarTypeAdapter extends RecyclerView.Adapter<AzkarTypeAdapter.AzkarTypeViewHolder> {

    private ArrayList<ZekrType> azkarTypes;
    private Context context;
    OnZekrTypeClickListener onZekrTypeClickListener;

    public AzkarTypeAdapter(OnZekrTypeClickListener onZekrTypeClickListener) {
        this.onZekrTypeClickListener = onZekrTypeClickListener;
    }

    public void changeData(HashSet<ZekrType> azkarTypes){
        this.azkarTypes= new ArrayList<>(azkarTypes);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AzkarTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_zekr,parent,false);
        return new AzkarTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AzkarTypeViewHolder holder, int position) {
            holder.bind(azkarTypes.get(position));
    }

    @Override
    public int getItemCount() {
        return azkarTypes==null?0:azkarTypes.size();
    }

    public interface OnZekrTypeClickListener{
        void onZekrTypeClick(String zekrType);
    }

    class AzkarTypeViewHolder extends RecyclerView.ViewHolder {
        TextView zekrName;
        public AzkarTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            zekrName= itemView.findViewById(R.id.zekr_name);
        }

        public void bind(ZekrType zekrType) {
            zekrName.setText(zekrType.getZekrName());
            itemView.setOnClickListener(view -> {
                onZekrTypeClickListener.onZekrTypeClick(zekrType.getZekrName());
            });
        }
    }
}
