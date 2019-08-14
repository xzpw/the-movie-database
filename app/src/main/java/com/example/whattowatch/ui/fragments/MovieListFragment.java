package com.example.whattowatch.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.whattowatch.R;
import com.example.whattowatch.ui.adaptor.EndlessScrollListner;
import com.example.whattowatch.ui.adaptor.ListAdaptor;
import com.example.whattowatch.model.mymodel.MyMovieModel;
import com.example.whattowatch.ui.presenter.MovieListPresenter;
import com.example.whattowatch.ui.view.MovieListsView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

public class MovieListFragment extends BaseFragment implements MovieListsView, SwipeRefreshLayout.OnRefreshListener {

    private static final String ARG_FRAGMENT_NUMBER = "current_fragment_number";
    private static final String LOG = "mylog";

    @InjectPresenter
    MovieListPresenter mMovieListPresenter;

    @ProvidePresenter()
    MovieListPresenter provideMoviesPresenter() {
        return new MovieListPresenter(getArguments().getInt(ARG_FRAGMENT_NUMBER));  // передача начальных параметров в презентор
    }

    private RecyclerView mRecyclerView;

    private AlertDialog progressDialog;

    private SwipeRefreshLayout swipeRefreshLayout;

    private EndlessScrollListner endlessScrollListner;

    private ListAdaptor listAdaptor;


    public static MovieListFragment newInstance(int currentFragmentNumber) {
        Bundle args = new Bundle();
        args.putInt(ARG_FRAGMENT_NUMBER, currentFragmentNumber);
        MovieListFragment fragment = new MovieListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_list_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("mylog", "MovieListFragment: onCreateView");
        initView(view);
    }

    private void initView(@NonNull View view) {
        mRecyclerView = view.findViewById(R.id.recycler);
        swipeRefreshLayout = view.findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(this);
        progressDialog = new MaterialAlertDialogBuilder(getActivity())
                .setCancelable(false)
                .setView(R.layout.dialog_progress)
                .show();
        progressDialog.hide();
        initRecycler();
    }

    private void initRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        listAdaptor = new ListAdaptor(null, getRouter());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(listAdaptor);

        endlessScrollListner = new EndlessScrollListner(layoutManager) {
            @Override
            public void loadMore(int page) {
                //переопределили абстрактрый метод, каждый раз инкриментируем страницу и загружаем
                mMovieListPresenter.loadMovieList(page);
                mRecyclerView.getAdapter().notifyDataSetChanged();
            }
        };
        mRecyclerView.addOnScrollListener(endlessScrollListner);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.e("mylog", "MovieListFragment: onSaveInstanceState");

        super.onSaveInstanceState(outState);

    }


    @Override
    public void showProgres(boolean isVisible) {
        if (isVisible) {
            progressDialog.show();
        } else {
            progressDialog.hide();
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("mylog","MovieListFragment Adapter count "+ listAdaptor.getItemCount());
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("mylog", "MovieListFragment: onResume()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("mylog", "MovieListFragment: onDestroy()");

    }

    @Override
    public void showMovies(List<MyMovieModel> movies) {
        Log.d(LOG, "showMovies() size: " + movies.size());
        listAdaptor.setMovieList(movies);
        listAdaptor.notifyDataSetChanged();

    }


    @Override
    public void showError() {
        Log.d(LOG, "showError()");
    }

    @Override
    public void onRefreshed() {

            swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onLoaded(List<MyMovieModel> data) {
        // обновляем данные адаптера
        listAdaptor.setMovieList(data);
        listAdaptor.notifyDataSetChanged();
        endlessScrollListner.setLoading(false);
    }


    @Override
    public void onRefresh() {
        mMovieListPresenter.loadMovieList();
    }
}
