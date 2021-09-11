package com.github.douglasbastos25.movieshowcase.data.repositories

import com.github.douglasbastos25.movieshowcase.data.services.TheMovieDBService
import kotlinx.coroutines.flow.flow

class GenreRepositoryImpl(private val service: TheMovieDBService) : GenreRepository {

    override suspend fun getGenresData() = flow {
        try {
            val genresResponse = service.getGenres()
            emit(genresResponse)
        } catch (e: Exception) {
            throw e
        }
    }
}
