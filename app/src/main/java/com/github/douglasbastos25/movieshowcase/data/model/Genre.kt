package com.github.douglasbastos25.movieshowcase.data.model

data class Genre(
    val id: Int,
    val name: String
)

data class GenresResponse(
    val genres: List<Genre>
)
