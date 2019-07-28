package com.example.whattowatch.ui.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.whattowatch.App;
import com.example.whattowatch.api.MovieService;
import com.example.whattowatch.model.mappers.DetailMapper;
import com.example.whattowatch.model.mymodel.MyDetailModel;
import com.example.whattowatch.repository.MoviesRepo;
import com.example.whattowatch.ui.view.DetailMovieView;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class DetailPresenter extends MvpPresenter<DetailMovieView> {

    private int MOVIE_ID;
    private MyDetailModel data = null;

    @Inject
    public MoviesRepo moviesRepo;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public DetailPresenter(int id){
        App.getInstance().getAppComponent().inject(this);
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
        if(data == null) {
            loadDetailMovie(MOVIE_ID);

        }
    }

    public void loadDetailMovie(int id) {
        Log.e("mylog", "ID movie is " + id);
//        Disposable d = MovieService.getApi()
//                .getDetail(id,MovieService.API_KEY,"en")
//                .map(data -> DetailMapper.convertToMyDetailModel(data))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(detailMovie -> {data = detailMovie;
//                    getViewState().showMovie(detailMovie);
//                },
//                      error->  {
//                            getViewState().showError();
//                      }
//                    );

        Disposable d = moviesRepo.getDetailMovie(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        movieDetail->{
                           data = movieDetail;
                           getViewState().showMovie(movieDetail);
                           Log.d("mylog","DetailMovie: onResponse" + data.getVideos().get(0).getKey());
                        },
                        error->{
                            getViewState().showError();
                            Log.d("mylog","DetailMovie: onError" + error.getMessage());
                        }
                );

        compositeDisposable.add(d);

    }
}
