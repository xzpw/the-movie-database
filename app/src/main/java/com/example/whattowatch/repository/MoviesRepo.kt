package com.example.whattowatch.repository

import android.util.Log
import com.example.whattowatch.model.mappers.*
import com.example.whattowatch.model.mymodel.*
import com.example.whattowatch.model.response.cradits.MovieCredits
import com.example.whattowatch.model.response.detail.DetailMovie
import com.example.whattowatch.model.response.list.MoviesObject
import com.example.whattowatch.model.response.person.Person
import com.example.whattowatch.model.response.video.MovieVideosResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class MoviesRepo @Inject constructor( val mRemoteMovieSource: RemoteMovieSource,  val mLocalMovieSource: LocalMovieSource) {
    private val FIRST_PAGE = 1
    fun getAllLocalMovies(type: String?): Flowable<List<MyMovieModel>> {
        return mLocalMovieSource.getAllMoviesByType(type)
    }

    fun getMoviesFirstPageByType(type: String): Flowable<List<MyMovieModel>> {
        return mRemoteMovieSource.getMoviesList(type, FIRST_PAGE)
                .map { data: MoviesObject? ->
                    data?.results?.let { MovieMapper.convertListToMyModel(it, type) } // преващаем в наш тип
                }
                .flatMap { localData: List<MyMovieModel> ->
                    mLocalMovieSource.deleteAllbyType(type) //удаляем старые записи
                            .andThen(mLocalMovieSource.insertAll(localData) //Записываем в бд
                                    .andThen(Flowable.just(localData))) // возвращаем результат
                }.onErrorResumeNext(mLocalMovieSource.getAllMoviesByType(type)) //в случае ошибки берем данные с БД
    }

    fun getMoviesPageByType(type: String, page: Int): Flowable<List<MyMovieModel>> {
        return mRemoteMovieSource.getMoviesList(type, page)
                .map { data: MoviesObject? ->
                    data!!.results?.let { MovieMapper.convertListToMyModel(it, type) } //загружаем остальные страници без сохранения
                }
    }

    fun getDetailMovieInfo(id: Int): Flowable<MyDetailModel> {
        return mRemoteMovieSource.getMovieDetail(id)
                .map { data: DetailMovie -> DetailMapper.convertToMyDetailModel(data) }
    }

    fun getMovieTrailers(id: Int?): Flowable<List<MyVideoModel>> {
        return mRemoteMovieSource.getMovieVideos(id)
                .map { data: MovieVideosResponse -> data.results?.let { VideoMapper.convertToMyVideoList(it) } }
    }

    fun searchMovie(query: String?, page: Int): Flowable<List<MyMovieModel>> {
        return mRemoteMovieSource.searchMovie(query, page)
                .map { data: MoviesObject -> data.results?.let { MovieMapper.convertListToMyModel(it, null) } }
    }

    val allFAvorites: Flowable<List<MyDetailModel>>
        get() = mLocalMovieSource.allFavorites

    fun add2Favorites(detailModel: MyDetailModel): Completable {
        Log.d("mylog", detailModel.name)
        return mLocalMovieSource.insertMovie2Favorites(detailModel)
    }

    fun getMovieCasts(id: Int?): Flowable<List<MyCastModel>> {
        return mRemoteMovieSource.getMovieCredits(id)
                .map { data: MovieCredits? -> data?.cast?.let { CastMapper.convertListMyCastModel(it) } }
    }

    fun getPersonDetail(id: Int): Flowable<MyPersonModel> {
        return mRemoteMovieSource.getPersonDetail(id)
                .map { data: Person? -> data?.let { PersonMapper.convertPerson(it) } }
    }

    fun checkSavedFavorites(id: Int): Flowable<MyDetailModel> {
        return mLocalMovieSource.checkSavedFavorites(id)
    }

    fun deleteFromFavorites(detailModel: MyDetailModel?): Completable {
        return mLocalMovieSource.deleteFromFavorites(detailModel)
    }

}