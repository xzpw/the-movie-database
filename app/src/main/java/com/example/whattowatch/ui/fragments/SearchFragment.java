package com.example.whattowatch.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.whattowatch.R;
import com.example.whattowatch.model.mymodel.MyMovieModel;
import com.example.whattowatch.ui.adaptor.EndlessScrollListner;
import com.example.whattowatch.ui.adaptor.ListAdaptor;
import com.example.whattowatch.ui.presenter.SearchPresenter;
import com.example.whattowatch.ui.view.MovieListsView;

import java.util.List;

public class SearchFragment extends BaseFragment implements MovieListsView {

    @InjectPresenter
    SearchPresenter mPresenter;
    private SearchView mSearchView;
    private RecyclerView mRecyclerView;
    private EndlessScrollListner endlessScrollListner;
    private ListAdaptor listAdaptor;
    private String searchQuery;
    private ImageView ivCloud;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container,false);
        Log.d("mylog","SearchFragment() onCreateView");
        initView(view);
        return view;
    }

    private void initView(View view) {
        mRecyclerView = view.findViewById(R.id.search_rv);
        mSearchView = view.findViewById(R.id.searchView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        listAdaptor = new ListAdaptor(null, getRouter());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(listAdaptor);
        ivCloud = view.findViewById(R.id.iv_cloud_off_search);
        progressBar = view.findViewById(R.id.progressBar_search);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.length() > 1){
                    progressBar.setVisibility(View.VISIBLE);
                    mPresenter.onSearch(newText,1,false);
                    searchQuery = newText;
                    return true;
                }
                return false;
            }
        });

        endlessScrollListner = new EndlessScrollListner(layoutManager) {
            @Override
            public void loadMore(int page) {

                mPresenter.onSearch(searchQuery,page,true);

            }
        };
        mRecyclerView.addOnScrollListener(endlessScrollListner);

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("mylog","SearchFragment() onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("mylog","SearchFragment() onDestroy");
    }

    @Override
    public void showProgres(boolean isVisible) {
        if(isVisible) {
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showMovies(List<MyMovieModel> movies) {
        ivCloud.setVisibility(View.INVISIBLE);
        listAdaptor.setMovieList(movies);
        listAdaptor.notifyDataSetChanged();
    }

    @Override
    public void showError() {
        ivCloud.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRefreshed() {

    }

    @Override
    public void onLoaded(List<MyMovieModel> data) {
        listAdaptor.setMovieList(data);
        listAdaptor.notifyDataSetChanged();
        endlessScrollListner.setLoading(false);
    }
}
