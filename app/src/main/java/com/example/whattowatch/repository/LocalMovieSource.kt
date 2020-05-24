package com.example.whattowatch.repository

import com.example.whattowatch.database.MovieDao
import com.example.whattowatch.model.mymodel.MyDetailModel
import com.example.whattowatch.model.mymodel.MyMovieModel
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class LocalMovieSource @Inject constructor(private val dao: MovieDao) {
    fun insertAll(movieModels: List<MyMovieModel>): Completable {
        return Completable.fromAction { dao.insertAllMovies(movieModels) }
    }

    fun getAllMoviesByType(type: String?): Flowable<List<MyMovieModel>> {
        return dao.getMoviesByType(type)
    }

    fun deleteAllbyType(type: String?): Completable {
        return Completable.fromAction { dao.deleteAllbyType(type) }
    }

    fun insertMovie2Favorites(detailModel: MyDetailModel?): Completable {
        return Completable.fromAction { dao.insertDetail(detailModel) }
    }

    fun deleteFromFavorites(detailModel: MyDetailModel?): Completable {
        return Completable.fromAction { dao.deleteFavorite(detailModel) }
    }

    val allFavorites: Flowable<List<MyDetailModel>>
        get() = dao.allFavorites

    fun checkSavedFavorites(id: Int): Flowable<MyDetailModel> {
        return dao.isSaved(id)
    }

}