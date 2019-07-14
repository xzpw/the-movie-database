package com.example.whattowatch.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.whattowatch.R;
import com.example.whattowatch.adaptor.ListAdaptor;
import com.example.whattowatch.model.MyMovieModel;
import com.example.whattowatch.presenter.MoviesPresenter;
import com.example.whattowatch.view.MovieListsView;

import java.util.List;

public class MovieListFragment extends MvpAppCompatFragment implements MovieListsView {

    private static final String ARG_FRAGMENT_NUMBER = "current_fragment_number";
    private static final String LOG = "mylog";

    @InjectPresenter
    MoviesPresenter mMoviesPresenter;

    @ProvidePresenter()
    MoviesPresenter provideMoviesPresenter(){
        return new MoviesPresenter( getArguments().getInt(ARG_FRAGMENT_NUMBER));  // передача начальных параметров в презентор
    }
    private RecyclerView mRecyclerView;


    public static MovieListFragment newInstance(int currentFragmentNumber) {
        Bundle args = new Bundle();
        args.putInt(ARG_FRAGMENT_NUMBER, currentFragmentNumber );
        MovieListFragment fragment = new MovieListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_list_fragment,container,false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.recycler);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public void showProgres() {
        Log.d(LOG,"showProgress() : "+getArguments().getInt(ARG_FRAGMENT_NUMBER));

    }

    @Override
    public void hideProgres() {
        Log.d(LOG,"hideProgress()");
    }

    @Override
    public void showMovies(List<MyMovieModel> movies) {
        Log.d(LOG,"showMovies() size: " + movies.size());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new ListAdaptor(movies));

    }

    @Override
    public void showError() {
        Log.d(LOG,"showError()");
    }


}
