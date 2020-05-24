package com.example.whattowatch.model.mappers

import android.util.Log
import com.example.whattowatch.model.mymodel.MyDetailModel
import com.example.whattowatch.model.mymodel.MyMovieModel
import java.util.*

object Detail2ListMapper {
    fun convert2ListModel(detailModelList: List<MyDetailModel>): List<MyMovieModel> {
        val list: MutableList<MyMovieModel> = ArrayList()
        for (detailModel in detailModelList) {
            val model = MyMovieModel(detailModel.name,
                    detailModel.overview,
                    detailModel.releaseDate,
                    detailModel.rate,
                    detailModel.posterLink,
                    detailModel.id,
                    "favorite")
            list.add(model)
            Log.e("mylog", "Detail2ListMapper")
        }
        return list
    }
}