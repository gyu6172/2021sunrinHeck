package com.example.sunrinheck2021;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<Item> items = new ArrayList<>();

    public RecyclerAdapter(ArrayList<Item> items) {
        this.items = items;
    }

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener mListener) {
        this.mListener = mListener;
    }

    public interface OnRecyclerItemClickListener{
        void onItemClick(View v, int position);
    }

    private OnRecyclerItemClickListener mListener = null;

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView titleTv;
        private TextView contentTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
            titleTv = itemView.findViewById(R.id.titleTv);
            contentTv = itemView.findViewById(R.id.contentTv);

            itemView.setOnClickListener(v->{

                int pos = getAdapterPosition();
                Log.e("isSelected",""+pos);

                if(pos != RecyclerView.NO_POSITION){
                    mListener.onItemClick(v, pos);
                }
            });
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item, parent, false);
        RecyclerAdapter.ViewHolder viewHolder = new RecyclerAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.titleTv.setText(item.getName());
        holder.contentTv.setText(item.getPrice()+"원");
        holder.imageView.setImageDrawable(item.getBackgroundImg());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}