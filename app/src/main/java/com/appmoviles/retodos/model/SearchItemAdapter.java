package com.appmoviles.retodos.model;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appmoviles.retodos.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchItemAdapter extends RecyclerView.Adapter<SearchItemAdapter.SearchItemViewHolder> {

    private ArrayList<SearchItem> items;
    private OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void OnClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class SearchItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgView;
        public TextView listNameTV;
        public TextView userTV;
        public TextView nSongsTV;
        public SearchItemViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            imgView = itemView.findViewById(R.id.imageView);
            listNameTV = itemView.findViewById(R.id.listNameTV);
            userTV = itemView.findViewById(R.id.userTV);
            nSongsTV = itemView.findViewById(R.id.nSongsTV);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener !=null) {
                        int position = getAdapterPosition();
                        if (position!=RecyclerView.NO_POSITION)
                            listener.onItemClick(position);
                    }
                }
            });
        }
    }

    public SearchItemAdapter(ArrayList<SearchItem> items) {
        this.items = items;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public SearchItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item,parent, false);
        SearchItemViewHolder h = new SearchItemViewHolder(v, listener);
        return h;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchItemViewHolder holder, int position) {
        SearchItem cItem = items.get(position);

        Picasso.get().load(cItem.getCover()).into(holder.imgView);
        holder.listNameTV.setText(cItem.getName());
        holder.userTV.setText(cItem.getUsername());
        holder.nSongsTV.setText(cItem.getnSongs());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void delete() {
        items.clear();
        notifyDataSetChanged();
    }

}
