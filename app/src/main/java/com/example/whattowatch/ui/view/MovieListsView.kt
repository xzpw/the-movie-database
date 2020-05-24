package com.example.whattowatch.ui.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.whattowatch.model.mymodel.MyMovieModel

@StateStrategyType(AddToEndSingleStrategy::class)
interface MovieListsView : MvpView {
    @StateStrategyType(SkipStrategy::class)
    fun showProgres(isVisible: Boolean)

    fun showMovies(movies: List<MyMovieModel>)
    fun showError()
    @StateStrategyType(SkipStrategy::class)
    fun onRefreshed()

    fun onLoaded(data: List<MyMovieModel>)
}