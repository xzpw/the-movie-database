package com.example.whattowatch.model.mappers

import com.example.whattowatch.model.mymodel.MyVideoModel
import com.example.whattowatch.model.response.video.MovieVideos
import java.util.*

object VideoMapper {
    fun convertToMyVideoModel(movieVideos: MovieVideos): MyVideoModel {
        return MyVideoModel(movieVideos.name, movieVideos.key)
    }

    fun convertToMyVideoList(movieVideos: List<MovieVideos>): List<MyVideoModel> {
        val videoModelList: MutableList<MyVideoModel> = ArrayList()
        for (mv in movieVideos) {
            videoModelList.add(convertToMyVideoModel(mv))
        }
        return videoModelList
    }
}