package com.github.douglasbastos25.movieshowcase.data.repositories

import com.github.douglasbastos25.movieshowcase.data.model.Configuration
import kotlinx.coroutines.flow.Flow

interface ConfigurationRepository {
    suspend fun getConfiguration(): Flow<Configuration>
}
