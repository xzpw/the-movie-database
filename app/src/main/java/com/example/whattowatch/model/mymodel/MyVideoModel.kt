package com.example.whattowatch.model.mymodel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class MyVideoModel(var name: String?, var key: String?) {

    @PrimaryKey
    var id: Int? = null

}