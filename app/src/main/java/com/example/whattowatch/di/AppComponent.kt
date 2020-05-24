package com.example.whattowatch.di

import com.example.whattowatch.MainActivity
import com.example.whattowatch.di.modules.DatabaseModule
import com.example.whattowatch.di.modules.NetworkModule
import com.example.whattowatch.di.modules.ViewModelModule
import com.example.whattowatch.ui.adaptor.ListAdaptor
import com.example.whattowatch.ui.favorite.FavoriteFragment
import com.example.whattowatch.ui.presenter.DetailPresenter
import com.example.whattowatch.ui.presenter.MovieListPresenter
import com.example.whattowatch.ui.presenter.PersonPresenter
import com.example.whattowatch.ui.presenter.SearchPresenter
import dagger.Component
import javax.inject.Singleton

@Component(modules = [DatabaseModule::class, NetworkModule::class, ViewModelModule::class])
@Singleton
interface AppComponent {
    fun inject(listAdaptor: ListAdaptor?)
    fun inject(mainActivity: MainActivity?)
    fun inject(movieListPresenter: MovieListPresenter?)
    fun inject(detailPresenter: DetailPresenter?)
    fun inject(searchPresenter: SearchPresenter?)
    fun inject(personPresenter: PersonPresenter?)
    fun inject(favoriteFragment: FavoriteFragment?)
}