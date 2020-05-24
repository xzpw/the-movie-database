package com.example.whattowatch.model.response.cradits

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieCredits {
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("cast")
    @Expose
    var cast: List<Cast>? = null
    @SerializedName("crew")
    @Expose
    var crew: List<Crew>? = null

}