package com.example.whattowatch.repository

import com.example.whattowatch.api.IMovieAPI
import com.example.whattowatch.model.response.cradits.MovieCredits
import com.example.whattowatch.model.response.detail.DetailMovie
import com.example.whattowatch.model.response.list.MoviesObject
import com.example.whattowatch.model.response.person.Person
import com.example.whattowatch.model.response.video.MovieVideosResponse
import com.example.whattowatch.ui.presenter.MovieListPresenter
import io.reactivex.Flowable
import javax.inject.Inject

class RemoteMovieSource @Inject constructor(var api: IMovieAPI) {
    private val language = "ru"
    private val region = "UA"

    fun getMoviesList(selection: String, page: Int): Flowable<MoviesObject> { //возвращаем список фильмов по категории
        return if (selection === MovieListPresenter.Type.UPCOMING.section) {
            api.getUpcommingMovies(selection, language, page, region)
        } else api.getMovies(selection, language, page)
    }

    fun getMovieDetail(id: Int): Flowable<DetailMovie> {
        return api.getDetail(id, language)
    }

    fun getMovieVideos(id: Int?): Flowable<MovieVideosResponse> {
        return api.getMovieVideos(id)
    }

    fun searchMovie(query: String?, page: Int): Flowable<MoviesObject> {
        return api.searchMovie(language, page, query)
    }

    fun getMovieCredits(id: Int?): Flowable<MovieCredits> {
        return api.getMovieCredits(id)
    }

    fun getPersonDetail(id: Int): Flowable<Person> {
        return api.getPersonDetail(id, language)
    }

    companion object {
        const val API_KEY = "6d7cf38fae41aabbd628fc135149f671"
        const val VIDEO_YOUTUBE_THUMBNAIL = "https://img.youtube.com/vi/%s/sddefault.jpg"
    }

}