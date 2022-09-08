package com.example.islamy.adapter.prayerTime;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.islamy.R;
import com.example.islamy.model.prayertime.MyTimings;

import java.util.ArrayList;

public class PrayerTimeAdapter extends RecyclerView.Adapter<PrayerTimeAdapter.PrayerTimeViewHolder>{

    private ArrayList<MyTimings> myTimings;

    public void setMyTimings(ArrayList<MyTimings> myTimings) {
        this.myTimings = myTimings;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PrayerTimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_prayer,parent,false);
        return new PrayerTimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PrayerTimeViewHolder holder, int position) {
        holder.bind(myTimings.get(position));
    }

    @Override
    public int getItemCount() {
        return myTimings ==null?0: myTimings.size();
    }

    class PrayerTimeViewHolder extends RecyclerView.ViewHolder{
        TextView prayerName,prayerTime;
        public PrayerTimeViewHolder(@NonNull View itemView) {
            super(itemView);
            prayerName=itemView.findViewById(R.id.prayer_name);
            prayerTime=itemView.findViewById(R.id.prayer_time);
        }

        public void bind(MyTimings timing) {
            prayerName.setText(timing.getPrayerName());
            prayerTime.setText(timing.getPrayerTime());
        }
    }
}
