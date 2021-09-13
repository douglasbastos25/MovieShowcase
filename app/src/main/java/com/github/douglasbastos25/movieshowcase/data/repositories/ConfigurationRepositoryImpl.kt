package com.github.douglasbastos25.movieshowcase.data.repositories

import com.github.douglasbastos25.movieshowcase.data.services.TheMovieDBService
import kotlinx.coroutines.flow.flow

class ConfigurationRepositoryImpl(private val service: TheMovieDBService) :
    ConfigurationRepository {
    override suspend fun getConfiguration() = flow {
        try {
            val getConfiguration = service.getConfiguration()
            emit(getConfiguration)
        } catch (e: Exception) {
            throw e
        }
    }
}
