package com.example.whattowatch.ui.view

import com.arellomobile.mvp.MvpView
import com.example.whattowatch.model.mymodel.MyCastModel
import com.example.whattowatch.model.mymodel.MyDetailModel
import com.example.whattowatch.model.mymodel.MyVideoModel

interface DetailMovieView : MvpView {
    fun showProgress(isVisible: Boolean)
    fun showMovieInfo(movie: MyDetailModel)
    fun showMovieTrailer(videoModel: List<MyVideoModel>)
    fun showMovieCast(castModel: List<MyCastModel>)
    fun showErrorCast()
    fun showErrorInfo()
    fun showErrorTrailer()
    fun onSave(isSaved: Boolean)
}