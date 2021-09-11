package com.github.douglasbastos25.movieshowcase.data.repositories

import com.github.douglasbastos25.movieshowcase.data.model.Configuration
import com.github.douglasbastos25.movieshowcase.data.model.GenresResponse
import com.github.douglasbastos25.movieshowcase.data.model.Movie
import com.github.douglasbastos25.movieshowcase.data.model.SimilarMoviesResponse
import com.github.douglasbastos25.movieshowcase.data.services.TheMovieDBService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class MovieRepositoryImplTest {

    val expectedMovie = Movie(
        42,
        "The Hitchhiker's Guide to the Galaxy",
        4200,
        42000.0,
        "https://image.tmdb.org/t/p/w500/8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg",
    )

    @Test
    fun returnMovieDataWhenCallingMovieDataWithMovieId() {
        runBlocking {
            val service: TheMovieDBService = object : TheMovieDBService {
                override suspend fun getMovie(id: Int): Movie {
                    return expectedMovie
                }

                override suspend fun getGenres(): GenresResponse {
                    TODO("Not Used In This Test")
                }

                override suspend fun getSimilarMovies(id: Int): SimilarMoviesResponse {
                    TODO("Not Used In This Test")
                }

                override suspend fun getConfiguration(): Configuration {
                    TODO("Not Used In This Test")
                }
            }
            val movieRepositoryImpl = MovieRepositoryImpl(service)
            val resultMovie = movieRepositoryImpl.getMovieData(expectedMovie.id)
            assertEquals(expectedMovie, resultMovie.first())
        }
    }
}