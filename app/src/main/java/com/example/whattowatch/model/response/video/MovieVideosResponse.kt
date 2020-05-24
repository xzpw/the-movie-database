package com.example.whattowatch.model.response.video

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieVideosResponse {
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("results")
    @Expose
    var results: List<MovieVideos>? = null

}