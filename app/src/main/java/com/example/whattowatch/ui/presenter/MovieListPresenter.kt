package com.example.whattowatch.ui.presenter

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.whattowatch.App
import com.example.whattowatch.App.Companion.instance
import com.example.whattowatch.model.mappers.Detail2ListMapper
import com.example.whattowatch.model.mymodel.MyDetailModel
import com.example.whattowatch.model.mymodel.MyMovieModel
import com.example.whattowatch.repository.MoviesRepo
import com.example.whattowatch.ui.view.MovieListsView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

@InjectViewState
class MovieListPresenter : MvpPresenter<MovieListsView?> {
    var compositeDisposable = CompositeDisposable()
    private val data: MutableList<MyMovieModel> = ArrayList()
    private var SECTION_TYPE: Type? = null
    @Inject
    lateinit var moviesRepo: MoviesRepo

    constructor() {}
    constructor(t: Int) {
        SECTION_TYPE = Type.values()[t] //задаем тип погучаемой страницы
    }

    init {
        App.appComponent?.inject(this)
    }

    enum class Type(val section: String) {
        POPULAR("popular"), TOP("top_rated"), UPCOMING("upcoming"), FAVORITE("favorite");

    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState!!.showProgres(true)
    }

    override fun attachView(view: MovieListsView?) {
        super.attachView(view)
        if (data.isEmpty()) { //Для устранения множественных загрузок
            loadMovieList()
        } else {
            viewState!!.showMovies(data)
            viewState!!.showProgres(false)
        }
    }

    fun loadMovieList() { //Для загрузки и обновления первой страницы
        val d: Disposable
        d = if (SECTION_TYPE != Type.FAVORITE) {
            moviesRepo!!.getMoviesFirstPageByType(SECTION_TYPE!!.section)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ movieList: List<MyMovieModel> ->
                        data.clear()
                        data.addAll(movieList)
                        viewState!!.showProgres(false)
                        viewState!!.showMovies(data)
                        viewState!!.onRefreshed()
                    }
                    ) { error: Throwable? -> viewState!!.showError() }
        } else {
            moviesRepo.allFAvorites
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map { data: List<MyDetailModel> -> Detail2ListMapper.convert2ListModel(data) }
                    .subscribe({ favoriteList: List<MyMovieModel> ->
                        data.clear()
                        data.addAll(favoriteList!!)
                        viewState!!.showProgres(false)
                        viewState!!.showMovies(data)
                        viewState!!.onRefreshed()
                    }) { error: Throwable -> Log.d("mylog", error.message) }
        }
        compositeDisposable.add(d)
    }

    fun loadMovieList(page: Int) { // Метод для пагинации
        val d = moviesRepo.getMoviesPageByType(SECTION_TYPE!!.section, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movieList: List<MyMovieModel> ->
                    data.addAll(movieList)
                    viewState!!.showProgres(false)
                    viewState!!.onLoaded(data) //new data in RV
                }
                ) { error: Throwable? -> viewState!!.showError() }
        compositeDisposable.add(d)
    }

    override fun detachView(view: MovieListsView?) {
        super.detachView(view)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    companion object {
        private const val LoG = "mylog"
    }
}