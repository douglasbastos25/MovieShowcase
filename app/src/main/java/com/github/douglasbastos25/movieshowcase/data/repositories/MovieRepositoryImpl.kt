package com.github.douglasbastos25.movieshowcase.data.repositories

import com.github.douglasbastos25.movieshowcase.data.model.Movie
import com.github.douglasbastos25.movieshowcase.data.services.TheMovieDBService
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class MovieRepositoryImpl(private val service: TheMovieDBService): MovieRepository {

    companion object{
        const val API_KEY = "31cd78d4506a33966a401205028b3dad"
    }

    override suspend fun getMovieData(id: Int) = flow<Movie> {
        try {
            val movie = service.getMovie(id, API_KEY)
            emit(movie)
        } catch (e: Exception){
            throw e
        }
    }
}