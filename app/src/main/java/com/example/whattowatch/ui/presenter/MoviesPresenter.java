package com.example.whattowatch.ui.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.whattowatch.App;
import com.example.whattowatch.repository.MoviesRepo;
import com.example.whattowatch.model.mymodel.MyMovieModel;
import com.example.whattowatch.ui.view.MovieListsView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class MoviesPresenter extends MvpPresenter<MovieListsView> {

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private List<MyMovieModel> data = new ArrayList<>();
    private static final String LoG= "mylog";
    private Type SECTION_TYPE = null;
    private final int FIRST_PAGE = 1;

    @Inject
    MoviesRepo moviesRepo;



    public MoviesPresenter(){}
    public MoviesPresenter(int t){
        App.getInstance().getAppComponent().inject(this);
        SECTION_TYPE = Type.values()[t];                //задаем тип погучаемой страницы
        Log.d(LoG, "MoviesPresenter()_type");
    }


    public enum Type {

        POPULAR("popular"),
        TOP("top_rated"),
        UPCOMING("upcoming"),
        NOW("now_playing");

        Type(String section) {
            this.section = section;
        }
        private String section;
        public String getSection() {
            return section;
        }
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showProgres(true);
        Log.d(LoG, "onFistViewAttach");

    }

    @Override
    public void attachView(MovieListsView view) {
        super.attachView(view);
        Log.d(LoG, "attachView");
        if(data.isEmpty()){             //Для устранения множественных загрузок
            loadMovieList();
        }   else  {
            getViewState().showMovies(data);
            getViewState().showProgres(false);
        }
    }


    public void loadMovieList() {
        Log.d(LoG,"loadMovieList()");
    //Для загрузки и обновления первой страницы
        Disposable d = moviesRepo.getMoviesFirstPageByType(SECTION_TYPE.getSection())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieList -> {
                            data.clear();
                            data.addAll(movieList);
                            getViewState().showProgres(false);
                            getViewState().showMovies(data);
                            getViewState().onRefreshed();

                },
                        error -> getViewState().showError());
        compositeDisposable.add(d);
    }


    public void loadMovieList( int page) {
        Log.d(LoG,"loadMovieList()");
        // Метод для загрузки страница, и обновления
        Disposable d = moviesRepo.getMoviesPageByType(SECTION_TYPE.getSection(),page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieList -> {

                            data.addAll(movieList);
                            Log.d(LoG,"Subscribe " + data.size());
                            getViewState().showProgres(false);
                            getViewState().onLoaded(data);              //new data in RV

                            },
                        error -> getViewState().showError());
        compositeDisposable.add(d);
    }


    @Override
    public void detachView(MovieListsView view) {
        super.detachView(view);
    }

    @Override
    public void onDestroy() {
        Log.d(LoG, "onDestroy "+SECTION_TYPE);
        super.onDestroy();
        compositeDisposable.clear();
    }
}


