package com.example.whattowatch.ui.presenter;

import android.util.Log;

import androidx.room.Insert;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.whattowatch.App;
import com.example.whattowatch.database.MovieDao;
import com.example.whattowatch.model.mymodel.MyCastModel;
import com.example.whattowatch.model.mymodel.MyDetailModel;
import com.example.whattowatch.model.mymodel.MyVideoModel;
import com.example.whattowatch.repository.MoviesRepo;
import com.example.whattowatch.ui.view.DetailMovieView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class DetailPresenter extends MvpPresenter<DetailMovieView> {

    private int MOVIE_ID;
    private MyDetailModel data = null;
    private List<MyVideoModel> videos = new ArrayList<>();
    private List<MyCastModel> casts = new ArrayList<>();

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
        getViewState().showProgress(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    @Override
    public void attachView(DetailMovieView view) {
        super.attachView(view);
        if(data == null) {
            loadDetailMovie(MOVIE_ID);

        }
    }

    public void add2Favorites(){
        Log.d("mylog","Add to Favorites" + data.getName());

        Disposable d = moviesRepo.add2Favorites(data)
                        .subscribeOn(Schedulers.io())
                        .subscribe();
        compositeDisposable.add(d);

    }

    public void loadDetailMovie(int id) {
        Log.e("mylog", "ID movie is " + id);

        Disposable d = moviesRepo.getDetailMovieInfo(id)
                  .subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(
                          detailInfo ->{
                             data = detailInfo;
                             getViewState().showMovieInfo(detailInfo);
                          },
                          error ->{
                              getViewState().showErrorInfo();
                          }
                  );
        compositeDisposable.add(d);
                    d = moviesRepo.getMovieTrailers(id)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(movieTrailer ->{
                                videos = movieTrailer;
                                getViewState().showMovieTrailer(movieTrailer);
                            }, error->{
                                getViewState().showErrorTrailer();
                                Log.e("mylog",error.getMessage());
                            } );
        compositeDisposable.add(d);

                    d = moviesRepo.getMovieCasts(id)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(castModels -> {
                                    casts = castModels;
                                    Log.d("mylog","castSize " + casts.size());
                                    getViewState().showMovieCast(casts);
                                    },error ->{
                                        getViewState().showErrorCast();
                                        Log.e("mylog",error.getMessage());
                                    }
                            );
    }
}
