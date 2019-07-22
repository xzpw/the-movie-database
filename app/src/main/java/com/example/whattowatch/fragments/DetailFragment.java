package com.example.whattowatch.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bumptech.glide.Glide;
import com.example.whattowatch.R;
import com.example.whattowatch.mdbApi.IMovieAPI;
import com.example.whattowatch.model.mymodel.MyDetailModel;
import com.example.whattowatch.model.response.detail.DetailMovie;
import com.example.whattowatch.navigation.Router;
import com.example.whattowatch.presenter.DetailPresenter;
import com.example.whattowatch.view.DetailMovieView;

public class DetailFragment extends BaseFragment implements DetailMovieView {

    private static final String ARG_MOVIE_ID = "movie_id";
    private TextView tvTitle, tvDate, tvOverView;//, tvRate;
    private ImageView ivPoster, ivBackDrop;
    private RatingBar rateBar;

    @InjectPresenter
    DetailPresenter presenter;

    @ProvidePresenter()
    DetailPresenter provideDetailPresenter(){
        Log.e("myLog", "Передаем в конструктор " + getArguments().getInt(ARG_MOVIE_ID));
        return new DetailPresenter(getArguments().getInt(ARG_MOVIE_ID));
    }

    public static DetailFragment newInstance(int movieId) {

        Bundle args = new Bundle();
        args.putInt(ARG_MOVIE_ID,movieId);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment_scrolling
                ,container,false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(@NonNull View view) {
        ivBackDrop = view.findViewById(R.id.backdrop);
        ivPoster = view.findViewById(R.id.poster);
        tvDate = view.findViewById(R.id.detail_date);
        //tvRate = view.findViewById(R.id.detail_rate);
        tvTitle = view.findViewById(R.id.detail_title);
        rateBar = view.findViewById(R.id.detail_rate);
        tvOverView = view.findViewById(R.id.detail_overview);

    }

    @Override
    public void showProgres() {

    }

    @Override
    public void hideProgres() {

    }

    @Override
    public void showMovie(MyDetailModel movie) {
        Glide.with(getContext())
                .load(IMovieAPI.BASE_PICTURE + movie.getBackdroLink())
                .fitCenter()
                .into(ivBackDrop);

        Glide.with(getContext())
                .load(IMovieAPI.BASE_PICTURE + movie.getPosterLink())
                .fitCenter()
                .into(ivPoster);

        tvDate.setText(movie.getReleaseDate());
        tvTitle.setText(movie.getName());
        rateBar.setRating(movie.getRate());
        tvOverView.setText(movie.getOverview());

    }

    @Override
    public void showError() {

    }
}
