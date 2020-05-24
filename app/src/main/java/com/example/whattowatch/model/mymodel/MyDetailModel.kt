package com.example.whattowatch.model.mymodel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class MyDetailModel {
    //    public Integer getLocalID() {
//        return localID;
//    }
//
//    public void setLocalID(Integer localID) {
//        this.localID = localID;
//    }
    @PrimaryKey
    var id: Int? = null
    var name: String? = null
    var posterLink: String? = null
    var backdroLink: String? = null
    var runtime: Int? = null
    var rate: Float? = null
    var overview: String? = null
    var releaseDate: String? = null

    constructor(id: Int?, name: String?, posterLink: String?, backdroLink: String?, runtime: Int?, rate: Float?, overview: String?, releaseDate: String?) {
        this.id = id
        this.name = name
        this.posterLink = posterLink
        this.backdroLink = backdroLink
        this.runtime = runtime
        this.rate = rate
        this.overview = overview
        this.releaseDate = releaseDate
    }

    constructor() {}

}