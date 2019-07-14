package com.example.whattowatch.adaptor;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.whattowatch.R;
import com.example.whattowatch.mdbApi.IMovieAPI;
import com.example.whattowatch.model.MyMovieModel;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class ListAdaptor extends RecyclerView.Adapter<ListAdaptor.MovieHolder> {

    private List<MyMovieModel> mMovieList = new ArrayList<>();
    public ListAdaptor(List<MyMovieModel> movies) {
        mMovieList = movies;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
         View view = LayoutInflater.from(viewGroup.getContext())
                 .inflate(R.layout.movie_list_item,viewGroup,false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder movieHolder, int i) {
        movieHolder.bind(i);
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView tvTitle, tvDate;
        private RatingBar ratingBar;


        public MovieHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.rv_imageView);
            tvDate = itemView.findViewById(R.id.rv_date);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            //tvRate = itemView.findViewById(R.id.rv_rate);

            tvTitle = itemView.findViewById(R.id.rv_title);
        }

        public void bind(int i){

            tvTitle.setText(mMovieList.get(i).getTitle());
            tvDate.setText(mMovieList.get(i).getDate());
            ratingBar.setRating(mMovieList.get(i).getRate());

            Glide.with(itemView.getContext())
                    .load(IMovieAPI.BASE_PICTURE+mMovieList.get(i).getImageLink())
                    .fitCenter()
                    .into(imageView);

        }
    }

}
