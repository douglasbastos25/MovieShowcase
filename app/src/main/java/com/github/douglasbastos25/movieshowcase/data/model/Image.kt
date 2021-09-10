package com.github.douglasbastos25.movieshowcase.data.model

import com.google.gson.annotations.SerializedName

class Image(
    @SerializedName("secure_base_url")
    val baseUrl: String,
    @SerializedName("poster_sizes")
    val posterSizes: List<String>
)