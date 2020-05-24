package com.example.whattowatch.ui.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.whattowatch.App
import com.example.whattowatch.App.Companion.instance
import com.example.whattowatch.model.mymodel.MyMovieModel
import com.example.whattowatch.repository.MoviesRepo
import com.example.whattowatch.ui.view.MovieListsView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

@InjectViewState
class SearchPresenter : MvpPresenter<MovieListsView?>() {
    @Inject
    lateinit var moviesRepo: MoviesRepo
    var mCompositeDisposable = CompositeDisposable()
    private var searchList = mutableListOf<MyMovieModel>()
    override fun attachView(view: MovieListsView?) {
        super.attachView(view)
        if (!searchList.isEmpty()) {
            viewState!!.showMovies(searchList)
        }
    }

    fun onSearch(query: String?, page: Int, isPagination: Boolean) {
        val d = moviesRepo.searchMovie(query, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { data ->
                            //                если подгружаем список, то добавляем иначе заменяем
                            if (isPagination) {
                                viewState!!.showProgres(false)
                                searchList.addAll(data)
                                viewState!!.onLoaded(searchList)
                            } else {
                                viewState!!.showProgres(false)
                                searchList = data as MutableList<MyMovieModel>
                                viewState!!.showMovies(searchList)
                            }
                        }
                ) { error: Throwable? ->
                    viewState!!.showProgres(false)
                    viewState!!.showError()
                }
        mCompositeDisposable.add(d)
    }

    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable.clear()
    }

    init {
        App.appComponent?.inject(this)
    }
}