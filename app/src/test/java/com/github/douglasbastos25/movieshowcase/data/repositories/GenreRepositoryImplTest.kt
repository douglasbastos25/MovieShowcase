package com.github.douglasbastos25.movieshowcase.data.repositories

import com.github.douglasbastos25.movieshowcase.data.model.*
import com.github.douglasbastos25.movieshowcase.data.services.TheMovieDBService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class GenreRepositoryImplTest {

    val expectedGenre = GenresResponse(
        listOf(
            Genre(
                76,
                "Adventure"
            ), Genre(
                32,
                "Science Fiction"
            )
        )
    )

    @Test
    fun returnGenresWhenCallingGetGenresData() {
        runBlocking {
            val service: TheMovieDBService = object : TheMovieDBService {
                override suspend fun getMovie(id: Int): Movie {
                    TODO("Not Used In This Test")
                }

                override suspend fun getGenres(): GenresResponse {
                    return expectedGenre
                }

                override suspend fun getSimilarMovies(id: Int): SimilarMoviesResponse {
                    TODO("Not Used In This Test")
                }

                override suspend fun getConfiguration(): Configuration {
                    TODO("Not Used In This Test")
                }

            }
            val genreRepositoryImpl = GenreRepositoryImpl(service)
            val genresResult = genreRepositoryImpl.getGenresData()
            assertEquals(expectedGenre, genresResult.first())
        }
    }

}