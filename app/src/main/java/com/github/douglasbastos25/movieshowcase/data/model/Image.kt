package com.github.douglasbastos25.movieshowcase.data.model

import com.google.gson.annotations.SerializedName

data class Image(

    @SerializedName("secure_base_url")
    val baseUrl: String

)

data class Configuration(
    val images: Image
)
