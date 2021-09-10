package com.github.douglasbastos25.movieshowcase.data.repositories

import com.github.douglasbastos25.movieshowcase.data.model.GenresResponse
import kotlinx.coroutines.flow.Flow

interface GenreRepository {
    suspend fun getGenresData(): Flow<GenresResponse>
}
