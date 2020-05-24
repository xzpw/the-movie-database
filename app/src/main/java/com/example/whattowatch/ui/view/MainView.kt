package com.example.whattowatch.ui.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.whattowatch.ui.Router

interface MainView : MvpView, Router {
    @StateStrategyType(SkipStrategy::class)
    override fun showPagerView()

    override fun showDetailView(idMovie: Int) // будет принимать итем для детализации
    override fun showFavorites()
}