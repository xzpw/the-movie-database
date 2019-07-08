package com.example.whattowatch.adaptor;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.whattowatch.R;

public class ListAdaptor extends RecyclerView.Adapter<ListAdaptor.MovieHolder> {

    public ListAdaptor() {
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder movieHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MovieHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView tvTitle, tvDate, tvRate, tvOverview;


        public MovieHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.rv_imageView);
            tvDate = itemView.findViewById(R.id.rv_date);
            tvRate = itemView.findViewById(R.id.rv_rate);
            tvOverview = itemView.findViewById(R.id.rv_overview);
            tvTitle = itemView.findViewById(R.id.rv_title);
        }
    }

}
