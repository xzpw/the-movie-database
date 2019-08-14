package com.example.whattowatch.di;

import com.example.whattowatch.MainActivity;
import com.example.whattowatch.ui.adaptor.ListAdaptor;
import com.example.whattowatch.di.modules.DatabaseModule;
import com.example.whattowatch.di.modules.NetworkModule;
import com.example.whattowatch.ui.presenter.DetailPresenter;
import com.example.whattowatch.ui.presenter.MovieListPresenter;
import com.example.whattowatch.ui.presenter.SearchPresenter;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {DatabaseModule.class, NetworkModule.class})

public interface AppComponent {

    void inject(ListAdaptor listAdaptor);
    void inject(MainActivity mainActivity);
    void inject(MovieListPresenter movieListPresenter);
    void inject(DetailPresenter detailPresenter);
    void inject(SearchPresenter searchPresenter);
}
