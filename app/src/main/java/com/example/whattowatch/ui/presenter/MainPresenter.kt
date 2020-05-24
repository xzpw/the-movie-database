package com.example.whattowatch.ui.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.whattowatch.ui.view.MainView

@InjectViewState
class MainPresenter : MvpPresenter<MainView?>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState!!.showPagerView() // при создании отображает основной экрн
    }
}