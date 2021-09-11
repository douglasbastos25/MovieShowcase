package com.github.douglasbastos25.movieshowcase.domain

import com.github.douglasbastos25.movieshowcase.core.UseCase
import com.github.douglasbastos25.movieshowcase.data.model.Movie
import com.github.douglasbastos25.movieshowcase.data.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMovieUseCase(private val repository: MovieRepository) : UseCase<Int, Movie>() {
    override suspend fun execute(param: Int): Flow<Movie> {
        return repository.getMovieData(param)
    }
}
