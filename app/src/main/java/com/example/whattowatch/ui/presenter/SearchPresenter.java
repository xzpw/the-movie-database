package com.example.whattowatch.ui.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.whattowatch.App;
import com.example.whattowatch.model.mymodel.MyMovieModel;
import com.example.whattowatch.repository.MoviesRepo;
import com.example.whattowatch.ui.view.MovieListsView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
@InjectViewState
public class SearchPresenter extends MvpPresenter<MovieListsView> {



    @Inject
    MoviesRepo moviesRepo;
    CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private List<MyMovieModel> searchList = new ArrayList<>();
    public SearchPresenter(){
        App.getInstance().getAppComponent().inject(this);
    }

    @Override
    public void attachView(MovieListsView view) {
        super.attachView(view);
        if(!searchList.isEmpty()){
            getViewState().showMovies(searchList);
        }
    }

    public void onSearch(String query, int page, boolean isPagination){
       Disposable d =  moviesRepo.searchMovie(query,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        data ->{
//                если подгружаем список, то добавляем иначе заменяем
                          if(isPagination){

                              searchList.addAll(data);
                              Log.d("mylog","isPagination"+ searchList.size());
                              getViewState().onLoaded(searchList);
                          }else {
                              searchList = data;
                              Log.d("mylog","Search data size" +data.size());
                              getViewState().showMovies(searchList);
                          }
                        }, error -> {
                            getViewState().showError();
                            Log.d("mylog",error.getMessage());
                        }
                );
       mCompositeDisposable.add(d);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }
}
