package com.example.whattowatch;

import android.os.Bundle;
import android.util.Log;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.whattowatch.ui.fragments.DetailFragment;
import com.example.whattowatch.ui.fragments.PagerHolderFragment;
import com.example.whattowatch.ui.presenter.MainPresenter;
import com.example.whattowatch.ui.view.MainView;

public class MainActivity extends MvpAppCompatActivity implements MainView{


    @InjectPresenter
    MainPresenter mMainPresenter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.getInstance().getAppComponent().inject(this);   // инжектим презентер
    }

    @Override
    public void shwoPagerView() {
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
                .addToBackStack("")
                .commit();
    }

    @Override
    public void showFavorits() {

    }
}
