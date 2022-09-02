package com.example.islamy.adapter.azkar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.islamy.R;
import com.example.islamy.model.azkar.Zekr;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AzkarListAdapter extends RecyclerView.Adapter<AzkarListAdapter.AzkarListViewHolder>{

    ArrayList<Zekr> azkar;

    @NonNull
    @Override
    public AzkarListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_zekr,parent,false);
        return new AzkarListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AzkarListViewHolder holder, int position) {
            holder.bind(azkar.get(position));
    }

    @Override
    public int getItemCount() {
        return azkar==null?0:azkar.size();
    }

    public void setAzkar(ArrayList<Zekr> azkar) {
        this.azkar = azkar;
        notifyDataSetChanged();
    }
    class AzkarListViewHolder extends RecyclerView.ViewHolder{
        TextView zekrText;
        public AzkarListViewHolder(@NonNull View itemView) {
            super(itemView);
            zekrText= itemView.findViewById(R.id.zekr_name);
        }

        public void bind(Zekr zekr) {
            zekrText.setText(zekr.getZekr());
        }
    }
}
