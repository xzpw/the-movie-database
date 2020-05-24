package com.example.whattowatch.model.mymodel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class MyMovieModel {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var title: String? = null
    var overview: String? = null
    var date: String? = null
    var rate: Float? = null
    var imageLink: String? = null
    var movieId: Int? = null
    var type: String? = null

    constructor(title: String?, overview: String?, date: String?, rate: Float?, imageLink: String?, idMovie: Int?, type: String?) {
        this.title = title
        this.overview = overview
        this.date = date
        this.rate = rate
        this.imageLink = imageLink
        movieId = idMovie
        this.type = type
    }

    constructor() {}

}