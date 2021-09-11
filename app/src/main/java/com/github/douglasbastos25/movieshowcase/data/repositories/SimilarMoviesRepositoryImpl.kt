package com.github.douglasbastos25.movieshowcase.data.repositories

import com.github.douglasbastos25.movieshowcase.data.services.TheMovieDBService
import kotlinx.coroutines.flow.flow

class SimilarMoviesRepositoryImpl(private val service: TheMovieDBService) :
    SimilarMoviesRepository {

    override suspend fun getSimilarMoviesData(id: Int) = flow {
        try {
            val similarMovies = service.getSimilarMovies(id)
            emit(similarMovies)
        } catch (e: Exception) {
            throw e
        }
    }

}
