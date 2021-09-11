package com.github.douglasbastos25.movieshowcase.domain

import com.github.douglasbastos25.movieshowcase.core.UseCase
import com.github.douglasbastos25.movieshowcase.data.model.Configuration
import com.github.douglasbastos25.movieshowcase.data.repositories.ConfigurationRepository
import kotlinx.coroutines.flow.Flow

class GetConfigurationUseCase(private val repository: ConfigurationRepository) :
    UseCase.NoParam<Configuration>() {
    override suspend fun execute(): Flow<Configuration> {
        return repository.getConfiguration()
    }

}
