package com.example.whattowatch.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.whattowatch.R;
import com.example.whattowatch.presenter.MoviesPresenter;
import com.example.whattowatch.view.MovieListsView;

public class MovieListFragment extends MvpAppCompatFragment implements MovieListsView {

    private static final String ARG_FRAGMENT_NUMBER = "current_fragment_number";

    @InjectPresenter
    MoviesPresenter mMoviesPresenter;

    TextView tv;

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
        tv = view.findViewById(R.id.textView);
        tv.setText("Is fragment "+getArguments().getInt(ARG_FRAGMENT_NUMBER));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //presenter.init(index);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public void showProgres() {

    }

    @Override
    public void hideProgres() {

    }

    @Override
    public void showMovies() {

    }

    @Override
    public void showError() {

    }

    public enum Type {
        FIRST, SECOND, THIRD, FOURTH
    }
}
