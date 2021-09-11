package com.github.douglasbastos25.movieshowcase.data.repositories

import com.github.douglasbastos25.movieshowcase.data.model.*
import com.github.douglasbastos25.movieshowcase.data.services.TheMovieDBService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class ConfigurationRepositoryImplTest {

    val expectedConfiguration = Configuration(
        Image(
            "https://image.tmdb.org/t/p/",
            listOf("w300", "w780", "w1280", "original"),
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
            val configurationRepositoryImpl = ConfigurationRepositoryImpl(service)
            val resultConfiguration = configurationRepositoryImpl.getConfiguration()
            assertEquals(expectedConfiguration, resultConfiguration.first())
        }
    }
}