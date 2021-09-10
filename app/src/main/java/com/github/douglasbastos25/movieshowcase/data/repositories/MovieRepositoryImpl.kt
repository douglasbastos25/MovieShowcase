package com.github.douglasbastos25.movieshowcase.data.repositories

import com.github.douglasbastos25.movieshowcase.data.model.Movie
import com.github.douglasbastos25.movieshowcase.data.services.TheMovieDBService
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class MovieRepositoryImpl(private val service: TheMovieDBService): MovieRepository {

    override suspend fun getMovieData(id: Int) = flow<Movie> {
        try {
            val movie = service.getMovie(id)
            emit(movie)
        } catch (e: Exception){
            throw e
        }
    }
}
