package com.github.douglasbastos25.movieshowcase.domain

import com.github.douglasbastos25.movieshowcase.core.UseCase
import com.github.douglasbastos25.movieshowcase.data.model.SimilarMoviesResponse
import com.github.douglasbastos25.movieshowcase.data.repositories.SimilarMoviesRepository
import kotlinx.coroutines.flow.Flow

class GetSimilarMoviesUseCase (private val repository: SimilarMoviesRepository): UseCase<Int, SimilarMoviesResponse>() {
    override suspend fun execute(param: Int): Flow<SimilarMoviesResponse> {
        return repository.getSimilarMoviesData(param)
    }
}
