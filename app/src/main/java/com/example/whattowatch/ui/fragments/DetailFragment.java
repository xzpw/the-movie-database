package com.example.whattowatch.ui.fragments;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bumptech.glide.Glide;
import com.example.whattowatch.R;
import com.example.whattowatch.api.IMovieAPI;
import com.example.whattowatch.model.mymodel.MyCastModel;
import com.example.whattowatch.model.mymodel.MyDetailModel;
import com.example.whattowatch.model.mymodel.MyVideoModel;
import com.example.whattowatch.ui.adaptor.CastAdaptor;
import com.example.whattowatch.ui.adaptor.VideoAdapter;
import com.example.whattowatch.ui.presenter.DetailPresenter;
import com.example.whattowatch.ui.view.DetailMovieView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class DetailFragment extends BaseFragment implements DetailMovieView{

    private static final String ARG_MOVIE_ID = "movie_id";
    private TextView tvTitle, tvDate, tvOverView;
    private ImageView ivPoster, ivBackDrop;
    private RatingBar rateBar;
    private RecyclerView rvTrailerVideo , rvCast;
    private FloatingActionButton fabFavotite;
    private CardView cvInfoError;
   // private SwipeRefreshLayout swipeRefreshLayout;

    @InjectPresenter
    DetailPresenter presenter;

    @ProvidePresenter()
    DetailPresenter provideDetailPresenter(){
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
        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.detail_fragment_scrolling
                ,container,false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//
//            }
//        });
    }

    private void initView(@NonNull View view) {
        ivBackDrop = view.findViewById(R.id.backdrop);
        ivPoster = view.findViewById(R.id.poster);
        tvDate = view.findViewById(R.id.detail_date);

        //tvRate = view.findViewById(R.id.detail_rate);
        tvTitle = view.findViewById(R.id.detail_title);
        rateBar = view.findViewById(R.id.detail_rate);
        tvOverView = view.findViewById(R.id.detail_overview);
        //swipeRefreshLayout = view.findViewById(R.id.swipe_container);
        cvInfoError = view.findViewById(R.id.detail_info_error);
        rvTrailerVideo = view.findViewById(R.id.rv_video_trailer);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        rvTrailerVideo.setLayoutManager(layoutManager);
        rvTrailerVideo.setItemAnimator(new DefaultItemAnimator());

        fabFavotite = view.findViewById(R.id.fab);
        fabFavotite.setOnClickListener(l -> presenter.add2Favorites());

        rvCast = view.findViewById(R.id.rv_cast);
        LinearLayoutManager layoutManagerCast = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvCast.setLayoutManager(layoutManagerCast);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rvCast);

    }


    @Override
    public void showProgress(boolean isVisible) {
        showDialog(isVisible);
    }

    @Override
    public void showMovieInfo(MyDetailModel movie) {
        cvInfoError.setVisibility(View.INVISIBLE);
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
    public void showMovieTrailer(List<MyVideoModel> videoModel) {
        rvTrailerVideo.setAdapter(new VideoAdapter(videoModel));
    }

    @Override
    public void showMovieCast(List<MyCastModel> castModel) {
        rvCast.setAdapter(new CastAdaptor(castModel, getRouter()));
    }

    @Override
    public void showErrorCast() {

    }

    @Override
    public void showErrorInfo() {
        cvInfoError.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorTrailer() {

    }

    @Override
    public void onSave(boolean isSaved) {
        if(isSaved){


            Snackbar.make(getView(),"Добавлено в избранное",2000).show();
        }else {

            Snackbar.make(getView(),"Удалено с избранного",2000).show();
        }
    }


}
