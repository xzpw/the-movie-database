package com.example.whattowatch.model.mappers

import com.example.whattowatch.model.mymodel.MyDetailModel
import com.example.whattowatch.model.response.detail.DetailMovie

object DetailMapper {
    fun convertToMyDetailModel(detail: DetailMovie): MyDetailModel {
        return MyDetailModel(
                detail.id,
                detail.title,
                detail.posterPath,
                detail.backdropPath,
                detail.runtime,
                detail.voteAverage?.toFloat(),
                detail.overview,
                detail.releaseDate)
    }
}