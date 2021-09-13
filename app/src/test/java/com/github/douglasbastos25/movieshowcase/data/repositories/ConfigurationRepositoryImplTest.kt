package com.github.douglasbastos25.movieshowcase.data.repositories

import com.github.douglasbastos25.movieshowcase.data.model.*
import com.github.douglasbastos25.movieshowcase.data.services.TheMovieDBService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class ConfigurationRepositoryImplTest {

    val expectedConfiguration = Configuration(
        Image(
            "https://image.tmdb.org/t/p/",
        )
    )

    @Test
    fun returnConfigurationWhenCallingGetConfiguration() {
        runBlocking {
            val service: TheMovieDBService = object : TheMovieDBService {
                override suspend fun getConfiguration(): Configuration {
                    return expectedConfiguration
                }

                override suspend fun getGenres(): GenresResponse {
                    TODO("Not Used In This Test")
                }

                override suspend fun getMovie(id: Int): Movie {
                    TODO("Not Used In This Test")
                }

                override suspend fun getSimilarMovies(id: Int): SimilarMoviesResponse {
                    TODO("Not Used In This Test")
                }
            }
            val resultConfiguration =
                ConfigurationRepositoryImpl(service).getConfiguration().first()
            assertEquals(
                expectedConfiguration.images.baseUrl,
                resultConfiguration.images.baseUrl
            )
        }
    }
}
