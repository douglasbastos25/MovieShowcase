package com.github.douglasbastos25.movieshowcase.data.repositories

import com.github.douglasbastos25.movieshowcase.data.model.*
import com.github.douglasbastos25.movieshowcase.data.services.TheMovieDBService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class SimilarMoviesRepositoryImplTest {

    val expectedSimilarMovies = SimilarMoviesResponse(
        listOf(
            SimilarMovie(
                15,
                "The World's End",
                "2013-02-15",
                listOf(3, 5, 8),
                "https://image.tmdb.org/t/p/w500/8u99gUM8aNqYLs25sTBQiXu0Gfv.jpg",
            ), SimilarMovie(
                90,
                "Shaun of the Dead",
                "2004-10-03",
                listOf(7, 1, 9),
                "https://image.tmdb.org/t/p/w500/8u99gUH8aNqKLs65sTTQiXu5Gfv.jpg",
            )
        )
    )

    @Test
    fun returnSimilarMoviesWhenCallingGetSimilarMoviesDataWithMovieId() {
        runBlocking {
            val service: TheMovieDBService = object : TheMovieDBService {
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
            val resultSimilarMovies =
                SimilarMoviesRepositoryImpl(service).getSimilarMoviesData(178).first()

            assertEquals(
                expectedSimilarMovies.results.first().id,
                resultSimilarMovies.results.first().id
            )
            assertEquals(
                expectedSimilarMovies.results.first().title,
                resultSimilarMovies.results.first().title
            )
            assertEquals(
                expectedSimilarMovies.results.first().date,
                resultSimilarMovies.results.first().date
            )
            assertEquals(
                expectedSimilarMovies.results.first().genres.joinToString(","),
                resultSimilarMovies.results.first().genres.joinToString(",")
            )
            assertEquals(
                expectedSimilarMovies.results.first().poster,
                resultSimilarMovies.results.first().poster
            )
            assertEquals(
                expectedSimilarMovies.results.last().id,
                resultSimilarMovies.results.last().id
            )
            assertEquals(
                expectedSimilarMovies.results.last().title,
                resultSimilarMovies.results.last().title
            )
            assertEquals(
                expectedSimilarMovies.results.last().date,
                resultSimilarMovies.results.last().date
            )
            assertEquals(
                expectedSimilarMovies.results.last().genres.joinToString(","),
                resultSimilarMovies.results.last().genres.joinToString(",")
            )
            assertEquals(
                expectedSimilarMovies.results.last().poster,
                resultSimilarMovies.results.last().poster
            )
        }
    }
}
