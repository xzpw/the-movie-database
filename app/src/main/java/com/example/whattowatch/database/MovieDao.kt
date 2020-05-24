package com.example.whattowatch.database

import androidx.room.*
import com.example.whattowatch.model.mymodel.MyDetailModel
import com.example.whattowatch.model.mymodel.MyMovieModel
import io.reactivex.Flowable

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMovies(movies: List<MyMovieModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetail(detailModel: MyDetailModel?)

    @Update
    fun updateAllMovies(movies: List<MyMovieModel>)

    @Query("SELECT * FROM MyMovieModel WHERE type = :type")
    fun getMoviesByType(type: String?): Flowable<List<MyMovieModel>>

    @Query("DELETE FROM MyMovieModel WHERE type = :type")
    fun deleteAllbyType(type: String?)

    @get:Query("SELECT * FROM MyDetailModel")
    val allFavorites: Flowable<List<MyDetailModel>>

    @Delete
    fun deleteFavorite(detailModel: MyDetailModel?)

    @Query("SELECT * FROM MyDetailModel WHERE id = :id")
    fun isSaved(id: Int): Flowable<MyDetailModel>
}