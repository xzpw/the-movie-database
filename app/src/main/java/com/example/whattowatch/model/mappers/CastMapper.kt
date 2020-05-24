package com.example.whattowatch.model.mappers

import com.example.whattowatch.model.mymodel.MyCastModel
import com.example.whattowatch.model.response.cradits.Cast
import java.util.*

object CastMapper {
    fun convertMyCastModel(cast: Cast): MyCastModel {
        return MyCastModel(cast.id ?: 0,
                cast.character,
                cast.name,
                cast.profilePath)
    }

    fun convertListMyCastModel(casts: List<Cast>): List<MyCastModel> {
        val myCastModels: MutableList<MyCastModel> = ArrayList()
        for (cast in casts) {
            myCastModels.add(convertMyCastModel(cast))
        }
        return myCastModels
    }
}