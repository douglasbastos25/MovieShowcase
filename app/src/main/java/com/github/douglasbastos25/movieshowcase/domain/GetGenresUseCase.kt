package com.github.douglasbastos25.movieshowcase.domain

import com.github.douglasbastos25.movieshowcase.core.UseCase
import com.github.douglasbastos25.movieshowcase.data.model.GenresResponse
import com.github.douglasbastos25.movieshowcase.data.repositories.GenreRepository
import kotlinx.coroutines.flow.Flow

class GetGenresUseCase(private val repository: GenreRepository) :
    UseCase.NoParam<GenresResponse>() {

    override suspend fun execute(): Flow<GenresResponse> {
        return repository.getGenresData()
    }

}
