package com.example.islamy.adapter.radio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.islamy.R;
import com.example.islamy.model.radio.RadioItem;

import java.util.List;

public class RadioAdapter extends RecyclerView.Adapter<RadioAdapter.RadioViewHolder> {

    List<RadioItem> channels;
    @NonNull
    @Override
    public RadioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_radio,parent,false);
        return new RadioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RadioViewHolder holder, int position) {
            holder.bind(channels.get(position));
        if(onPlayItemClick!=null){
            holder.channelPlay.setOnClickListener(view -> {
                onPlayItemClick.onItemClick(position,channels.get(position));
            });
        }
        if(onStopItemClick!=null){
            holder.channelStop.setOnClickListener(view -> {
                onStopItemClick.onItemClick(position,channels.get(position));
            });
        }
    }

    public void setChannels(List<RadioItem> channels) {
        this.channels = channels;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return channels==null?0:channels.size();
    }

    public interface OnItemClick{
        void onItemClick(int position,RadioItem item);
    }

    public OnItemClick onPlayItemClick;
    public OnItemClick onStopItemClick;


    class RadioViewHolder extends RecyclerView.ViewHolder {
        TextView channelName;
        ImageView channelPlay,channelStop;
        public RadioViewHolder(@NonNull View itemView) {
            super(itemView);
            channelName=itemView.findViewById(R.id.channel_name);
            channelPlay=itemView.findViewById(R.id.channel_play);
            channelStop=itemView.findViewById(R.id.channel_stop);
        }

        public void bind(RadioItem radioItem) {
            channelName.setText( radioItem.getName());
        }
    }
}
