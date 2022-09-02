package com.example.islamy.adapter.ahadeth;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.islamy.R;
import com.example.islamy.model.ahadeth.AhadeethList;

import java.util.List;

public class AhadethListAdapter extends RecyclerView.Adapter<AhadethListAdapter.AhadetListViewHolder>{

    private List<AhadeethList> ahadeethList;
    private OnHadeethClickListener onHadeethClickListener;

    public AhadethListAdapter(List<AhadeethList> ahadeethList, OnHadeethClickListener onHadeethClickListener) {
        this.ahadeethList = ahadeethList;
        this.onHadeethClickListener = onHadeethClickListener;
    }

    @NonNull
    @Override
    public AhadetListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hadeeth_name,parent,false);
        return new AhadetListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AhadetListViewHolder holder, int position) {

        holder.bind(ahadeethList.get(position));
        if (onHadeethClickListener!=null){
            holder.itemView.setOnClickListener(view -> {
                onHadeethClickListener.onHadeethClick(position);
            });
        }
    }

    @Override
    public int getItemCount() {
        return ahadeethList.size();
    }


    public interface OnHadeethClickListener{
        void onHadeethClick(int position);
    }

    class AhadetListViewHolder extends RecyclerView.ViewHolder{
        TextView hadethTitle;
       public AhadetListViewHolder(@NonNull View itemView) {
           super(itemView);
           hadethTitle= itemView.findViewById(R.id.hadeeth_title);
       }

        public void bind(AhadeethList ahadeethList) {
            hadethTitle.setText(ahadeethList.getName());
        }
    }
}
