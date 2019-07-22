package com.example.whattowatch.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.whattowatch.Repository.IMovieRepository;
import com.example.whattowatch.fragments.DetailFragment;
import com.example.whattowatch.mdbApi.MovieService;
import com.example.whattowatch.model.mappers.DetailMapper;
import com.example.whattowatch.model.mymodel.MyDetailModel;
import com.example.whattowatch.model.response.list.Movie;
import com.example.whattowatch.view.DetailMovieView;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class DetailPresenter extends MvpPresenter<DetailMovieView> {

    private int MOVIE_ID;
    private MyDetailModel data = null;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public DetailPresenter(int id){
        MOVIE_ID = id;
    }

    public DetailPresenter(){}

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showProgres();
    }

    @Override
    public void attachView(DetailMovieView view) {
        super.attachView(view);
        if(data == null) loadDetailMovie(MOVIE_ID);
    }

    public void loadDetailMovie(int id) {
        Log.e("mylog", "ID movie is " + id);
        Disposable d = MovieService.getApi()
                .getDetail(id,MovieService.API_KEY,"en")
                .map(data -> DetailMapper.convertToMyDetailModel(data))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(detailMovie -> {data = detailMovie;
                    getViewState().showMovie(detailMovie);
                },
                      error->  {
                            getViewState().showError();
                      }
                    );

    }
}
