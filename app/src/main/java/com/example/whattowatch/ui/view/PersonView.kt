package com.example.whattowatch.ui.view

import com.arellomobile.mvp.MvpView
import com.example.whattowatch.model.mymodel.MyPersonModel

interface PersonView : MvpView {
    fun showProgress(isVisible: Boolean)
    fun showPersonInfo(personModel: MyPersonModel?)
    fun showError()
}