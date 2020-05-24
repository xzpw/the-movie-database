package com.example.whattowatch.ui.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.whattowatch.App
import com.example.whattowatch.App.Companion.instance
import com.example.whattowatch.model.mymodel.MyCastModel
import com.example.whattowatch.model.mymodel.MyDetailModel
import com.example.whattowatch.model.mymodel.MyVideoModel
import com.example.whattowatch.repository.MoviesRepo
import com.example.whattowatch.ui.view.DetailMovieView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

@InjectViewState
class DetailPresenter : MvpPresenter<DetailMovieView?> {
    private var MOVIE_ID = 0
    private var data: MyDetailModel? = null
    private var videos: List<MyVideoModel> = ArrayList()
    private var casts: List<MyCastModel> = ArrayList()
    @Inject
    lateinit var moviesRepo: MoviesRepo
    private var isSaved = false
    private val compositeDisposable = CompositeDisposable()

    constructor(id: Int) {
        MOVIE_ID = id
    }
    init {
        App.appComponent?.inject(this)
    }

    constructor() {}

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        checkSavedInDb()
        viewState!!.showProgress(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    override fun attachView(view: DetailMovieView?) {
        super.attachView(view)
        if (data == null) {
            loadDetailMovie(MOVIE_ID)
        }
    }

    fun add2Favorites() {
        isSaved = if (isSaved) {
            moviesRepo.deleteFromFavorites(data)
                    .subscribeOn(Schedulers.io())
                    .subscribe()
            false
        } else {
            val d = data?.let {
                moviesRepo!!.add2Favorites(it)
                        .subscribeOn(Schedulers.io())
                        .subscribe()
            }
            if (d != null) {
                compositeDisposable.add(d)
            }
            true
        }
        viewState!!.onSave(isSaved)
    }

    fun loadDetailMovie(id: Int) {
        var d = moviesRepo!!.getDetailMovieInfo(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { detailInfo: MyDetailModel? ->
                            viewState!!.showProgress(false)
                            data = detailInfo
                            viewState!!.showMovieInfo(detailInfo)
                        }
                ) { error: Throwable? ->
                    viewState!!.showProgress(false)
                    viewState!!.showErrorInfo()
                }
        compositeDisposable.add(d!!)
        d = moviesRepo!!.getMovieTrailers(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movieTrailer: List<MyVideoModel> ->
                    videos = movieTrailer
                    viewState!!.showMovieTrailer(movieTrailer)
                }) { error: Throwable? -> viewState!!.showErrorTrailer() }
        compositeDisposable.add(d)
        d = moviesRepo!!.getMovieCasts(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ castModels: List<MyCastModel> ->
                    casts = castModels
                    viewState!!.showMovieCast(casts)
                }
                ) { error: Throwable? -> viewState!!.showErrorCast() }
        compositeDisposable.add(d)
    }

    private fun checkSavedInDb() {
        val d = moviesRepo!!.checkSavedFavorites(MOVIE_ID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { itIsSaved: MyDetailModel? -> isSaved = true }
        compositeDisposable.add(d)
    }
}