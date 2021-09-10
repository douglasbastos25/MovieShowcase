package com.github.douglasbastos25.movieshowcase.data.model

import com.google.gson.annotations.SerializedName

data class SimilarMovie(

    val id: Int,

    @SerializedName("original_title")
    val title: String,

    @SerializedName("release_date")
    val date: String,

    @SerializedName("genre_ids")
    val genres: List<Int>

)
