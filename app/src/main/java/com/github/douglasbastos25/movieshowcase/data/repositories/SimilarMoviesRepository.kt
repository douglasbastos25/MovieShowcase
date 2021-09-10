package com.github.douglasbastos25.movieshowcase.data.repositories

import com.github.douglasbastos25.movieshowcase.data.model.SimilarMoviesResponse
import kotlinx.coroutines.flow.Flow

interface SimilarMoviesRepository {
    suspend fun getSimilarMoviesData(id: Int): Flow<SimilarMoviesResponse>
}
