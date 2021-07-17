package com.example.sunrinheck2021;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class marimoAdapter extends RecyclerView.Adapter<marimoAdapter.ViewHolder> {

    ArrayList<marimoItem> items =  new ArrayList<>();

    public marimoAdapter(ArrayList<marimoItem> items) {
        this.items = items;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView marimoName,marimoTime,marimoDia;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            marimoName=itemView.findViewById(R.id.marimoName);
            marimoTime=itemView.findViewById(R.id.marimoTime);
            marimoDia=itemView.findViewById(R.id.marimoDia);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.marimoitem, parent, false);
        marimoAdapter.ViewHolder viewHolder = new marimoAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        marimoItem item = items.get(position);
        holder.marimoName.setText(item.getName());
        holder.marimoTime.setText(item.getTime()+"일");
        holder.marimoDia.setText(item.getDiameter()+"mm");
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}