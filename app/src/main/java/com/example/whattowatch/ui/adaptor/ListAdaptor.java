package com.example.whattowatch.ui.adaptor;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.whattowatch.App;
import com.example.whattowatch.R;
import com.example.whattowatch.api.IMovieAPI;
import com.example.whattowatch.model.mymodel.MyMovieModel;
import com.example.whattowatch.ui.Router;

import java.util.ArrayList;
import java.util.List;

public class ListAdaptor extends RecyclerView.Adapter<ListAdaptor.MovieHolder> {

    private List<MyMovieModel> mMovieList = new ArrayList<>();
    private Router navigationRouter;

    public ListAdaptor(List<MyMovieModel> movies, Router router) {

        navigationRouter = router;
        mMovieList = movies;
        App.getInstance().getAppComponent().inject(this);
    }

    public void setMovieList(List<MyMovieModel> data){
        mMovieList = data;
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
        if (mMovieList == null){
            return 0;
        }else {
            return mMovieList.size();
        }
    }

    public class MovieHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView tvTitle, tvDate, tvRate;
        private RatingBar ratingBar;


        public MovieHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.rv_imageView);
            tvDate = itemView.findViewById(R.id.rv_date);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            tvRate = itemView.findViewById(R.id.rv_rate);

            tvTitle = itemView.findViewById(R.id.rv_title);
        }

        public void bind(int i){

            tvTitle.setText(mMovieList.get(i).getTitle());
            tvDate.setText(mMovieList.get(i).getDate());
            ratingBar.setRating(mMovieList.get(i).getRate());
            tvRate.setText(mMovieList.get(i).getRate() + "/10");
            Glide.with(itemView.getContext())
                    .load(IMovieAPI.BASE_PICTURE+mMovieList.get(i).getImageLink())
                    .fitCenter()
                    .into(imageView);

            itemView.setOnClickListener(l->{

                navigationRouter.showDetailView(mMovieList.get(i).getMovieId());
            });

        }
    }

}
