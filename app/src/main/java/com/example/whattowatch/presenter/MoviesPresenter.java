package com.example.whattowatch.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.whattowatch.Repository.IMovieRepository;
import com.example.whattowatch.mdbApi.MovieService;
import com.example.whattowatch.model.mappers.MovieMapper;
import com.example.whattowatch.model.mymodel.MyMovieModel;
import com.example.whattowatch.view.MovieListsView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class MoviesPresenter extends MvpPresenter<MovieListsView> implements IMovieRepository {

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private List<MyMovieModel> data = new ArrayList<>();
    private final String language = "en";
    IMovieRepository repo;
    private static final String LoG= "mylog";
    private Type SECTION_TYPE = null;



    public MoviesPresenter(){}
    public MoviesPresenter(int t){
        repo = this;
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
        Log.d(LoG, "onFistViewAttach");

    }

    @Override
    public void attachView(MovieListsView view) {
        super.attachView(view);
        Log.d(LoG, "attachView");
        if(data.isEmpty()){             //Для устранения множественных загрузок
            loadMovieList(SECTION_TYPE.getSection(),1);
        }   else  {
            getViewState().showMovies(data);
        }
    }





    @Override
    public void loadMovieList(String selection, int page) {
        Log.d(LoG,"loadMovieList()");
        Disposable d = MovieService.getApi()
                .getMovies(selection, language, page, MovieService.API_KEY)
                .map(data -> MovieMapper.convertListToMyModel(data.getResults()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieList -> {
                        data.addAll(movieList);
                        getViewState().hideProgres();
                        getViewState().showMovies(data);
                        },
                        error -> getViewState().showError()
                );
        compositeDisposable.add(d);
    }

    @Override
    public void loadDetailMovie(int id) {

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


