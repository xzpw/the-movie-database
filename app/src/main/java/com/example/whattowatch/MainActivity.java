package com.example.whattowatch;

import android.os.Bundle;
import android.util.Log;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.whattowatch.di.modules.MainPresenterModule;
import com.example.whattowatch.fragments.DetailFragment;
import com.example.whattowatch.fragments.PagerHolderFragment;
import com.example.whattowatch.presenter.MainPresenter;
import com.example.whattowatch.view.MainView;

import javax.inject.Inject;

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
                .replace(R.id.cont, DetailFragment.newInstance(id))
                .addToBackStack("")
                .commit();
    }

    @Override
    public void showFavorits() {

    }
}
