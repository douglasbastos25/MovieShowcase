package com.github.douglasbastos25.movieshowcase.data.model

import com.google.gson.annotations.SerializedName

data class Movie(

    @SerializedName("movie_id")
    val id: Int,

    @SerializedName("original_title")
    val title: String,

    @SerializedName("vote_count")
    val likes: Int,

    @SerializedName("popularity")
    val views: Double,

    @SerializedName("poster_path")
    val poster: String

)
