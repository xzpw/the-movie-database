package com.example.whattowatch;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.whattowatch.fragments.PagerHolderFragment;
import com.example.whattowatch.presenter.MainPresenter;
import com.example.whattowatch.view.MainView;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @InjectPresenter
    MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void shwoPagerView() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.cont,new PagerHolderFragment())
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void showDetailView() {

    }

    @Override
    public void showFavorits() {

    }
}
