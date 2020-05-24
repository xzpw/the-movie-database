package com.example.whattowatch.ui.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.whattowatch.App
import com.example.whattowatch.App.Companion.instance
import com.example.whattowatch.model.mymodel.MyPersonModel
import com.example.whattowatch.repository.MoviesRepo
import com.example.whattowatch.ui.view.PersonView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class PersonPresenter(private val PERSON_ID: Int) : MvpPresenter<PersonView?>() {
    private var person: MyPersonModel? = null
    private val compositeDisposable = CompositeDisposable()
    @Inject
    lateinit var moviesRepo: MoviesRepo

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState!!.showProgress(true)
    }

    override fun attachView(view: PersonView?) {
        super.attachView(view)
        if (person == null) {
            loadPersonInfo()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    fun loadPersonInfo() {
        val d = moviesRepo!!.getPersonDetail(PERSON_ID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data: MyPersonModel? ->
                    person = data
                    viewState!!.showPersonInfo(person)
                    viewState!!.showProgress(false)
                }) { error: Throwable? -> viewState!!.showError() }
        compositeDisposable.add(d)
    }

    init {
        App.appComponent?.inject(this)
    }
}