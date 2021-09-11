package com.github.douglasbastos25.movieshowcase.data.model

data class Genre(
    val id: Int,
    val name: String
)

class GenresResponse(
    val genres: List<Genre>
)
