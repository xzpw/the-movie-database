package com.example.whattowatch;

import android.os.Bundle;
import android.util.Log;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.whattowatch.ui.fragments.DetailFragment;
import com.example.whattowatch.ui.fragments.MovieListFragment;
import com.example.whattowatch.ui.fragments.PagerHolderFragment;
import com.example.whattowatch.ui.fragments.SearchFragment;
import com.example.whattowatch.ui.presenter.MainPresenter;
import com.example.whattowatch.ui.view.MainView;

public class MainActivity extends MvpAppCompatActivity implements MainView{


    @InjectPresenter
    MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void showPagerView() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.cont,new PagerHolderFragment())
                .commit();
    }

    @Override
    public void showDetailView(int id) {
        Log.d("mylog","MainActivity showDetailView");
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.cont, DetailFragment.newInstance(id))
                .addToBackStack("Detail")
                .commit();
    }

    @Override
    public void showFavorites() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.cont, MovieListFragment.newInstance(3))
                .addToBackStack("")
                .commit();

    }

    @Override
    public void showSearch() {
        Log.d("mylog","MainActivity showSearch");
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("Search")
                .add(R.id.cont,new SearchFragment())
                .commit();

    }
}
