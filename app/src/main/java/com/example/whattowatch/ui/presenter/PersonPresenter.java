package com.example.whattowatch.ui.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.whattowatch.App;
import com.example.whattowatch.model.mymodel.MyPersonModel;
import com.example.whattowatch.repository.MoviesRepo;
import com.example.whattowatch.ui.view.PersonView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class PersonPresenter extends MvpPresenter<PersonView> {
    private int PERSON_ID;
    private MyPersonModel person = null;
    private CompositeDisposable compositeDisposable;

    @Inject
    MoviesRepo moviesRepo;

    public PersonPresenter(int PERSON_ID) {
        this.PERSON_ID = PERSON_ID;
        App.getInstance().getAppComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showProgress(true);
    }

    @Override
    public void attachView(PersonView view) {
        super.attachView(view);
        if(person == null){
            loadPersonInfo();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    void loadPersonInfo(){
        Disposable d = moviesRepo.getPersonDetail(PERSON_ID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data ->{
                    person = data;
                    getViewState().showPersonInfo(person);
                    getViewState().showProgress(false);
                }, error ->{
                    getViewState().showError();
                });
//        compositeDisposable.add(d);
    }
}
