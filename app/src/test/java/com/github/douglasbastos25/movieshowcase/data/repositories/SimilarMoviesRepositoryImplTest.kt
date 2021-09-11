package com.github.douglasbastos25.movieshowcase.data.repositories

import com.github.douglasbastos25.movieshowcase.data.model.*
import com.github.douglasbastos25.movieshowcase.data.services.TheMovieDBService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class SimilarMoviesRepositoryImplTest {

    val expectedSimilarMovies = SimilarMoviesResponse(
        listOf(
            SimilarMovie(
                15,
                "The World's End",
                "2013",
                listOf(3, 5, 8),
                "https://image.tmdb.org/t/p/w500/8u99gUM8aNqYLs25sTBQiXu0Gfv.jpg",
            ), SimilarMovie(
                90,
                "Shaun of the Dead",
                "2004",
                listOf(7, 1, 9),
                "https://image.tmdb.org/t/p/w500/8u99gUH8aNqKLs65sTTQiXu5Gfv.jpg",
            )
        )
    )

    @Test
    fun returnSimilarMoviesWhenCallingGetSimilarMoviesDataWithMovieId(){
        runBlocking {
            val service: TheMovieDBService = object: TheMovieDBService{
                override suspend fun getMovie(id: Int): Movie {
                    TODO("Not Used In This Test")
                }

                override suspend fun getGenres(): GenresResponse {
                    TODO("Not Used In This Test")
                }

                override suspend fun getSimilarMovies(id: Int): SimilarMoviesResponse {
                    return expectedSimilarMovies
                }

                override suspend fun getConfiguration(): Configuration {
                    TODO("Not Used In This Test")
                }

            }
            val similarMoviesRepositoryImpl = SimilarMoviesRepositoryImpl(service)
            val resultSimilarMovies = similarMoviesRepositoryImpl.getSimilarMoviesData(178)
            assertEquals(expectedSimilarMovies, resultSimilarMovies.first())
        }
    }
}