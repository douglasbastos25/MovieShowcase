package com.github.douglasbastos25.movieshowcase.data.repositories

import com.github.douglasbastos25.movieshowcase.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovieData(id: Int): Flow<Movie>
}
