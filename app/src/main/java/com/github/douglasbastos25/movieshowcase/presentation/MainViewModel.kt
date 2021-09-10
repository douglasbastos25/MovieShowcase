package com.github.douglasbastos25.movieshowcase.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.douglasbastos25.movieshowcase.data.model.GenresResponse
import com.github.douglasbastos25.movieshowcase.data.model.Movie
import com.github.douglasbastos25.movieshowcase.data.model.SimilarMoviesResponse
import com.github.douglasbastos25.movieshowcase.domain.GetGenresUseCase
import com.github.douglasbastos25.movieshowcase.domain.GetMovieUseCase
import com.github.douglasbastos25.movieshowcase.domain.GetSimilarMoviesUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel(
    private val getMovieUseCase: GetMovieUseCase,
    private val getGenresUseCase: GetGenresUseCase,
    private val getSimilarMoviesUseCase: GetSimilarMoviesUseCase
) : ViewModel() {

    private val _movie = MutableLiveData<MovieState>()
    val movie: LiveData<MovieState> = _movie

    private val _genresObject = MutableLiveData<GenresState>()
    val genresObject: LiveData<GenresState> = _genresObject

    private val _similarMoviesObject = MutableLiveData<SimilarMoviesState>()
    val similarMoviesObject: LiveData<SimilarMoviesState> = _similarMoviesObject

    fun getMovie(id: Int) {
        viewModelScope.launch {
            getMovieUseCase(id)
                .onStart {
                    _movie.postValue(MovieState.Loading)
                }.catch {
                    _movie.postValue(MovieState.Error(it))
                }.collect {
                    _movie.postValue(MovieState.Success(it))
                }
        }
    }

    fun getGenres() {
        viewModelScope.launch {
            getGenresUseCase()
                .onStart {
                    _genresObject.postValue(GenresState.Loading)
                }.catch {
                    _genresObject.postValue(GenresState.Error(it))
                }.collect {
                    _genresObject.postValue(GenresState.Success(it))
                }
        }
    }

    fun getSimilarMovies(id: Int) {
        viewModelScope.launch {
            getSimilarMoviesUseCase(id)
                .onStart {
                    _similarMoviesObject.postValue(SimilarMoviesState.Loading)
                }.catch {
                    _similarMoviesObject.postValue(SimilarMoviesState.Error(it))
                }.collect {
                    _similarMoviesObject.postValue(SimilarMoviesState.Success(it))
                }
        }

    }


    sealed class MovieState {
        object Loading : MovieState()
        data class Success(val movie: Movie) : MovieState()
        data class Error(val error: Throwable) : MovieState()
    }

    sealed class GenresState {
        object Loading : GenresState()
        data class Success(val genresResponse: GenresResponse) : GenresState()
        data class Error(val error: Throwable) : GenresState()
    }

    sealed class SimilarMoviesState {
        object Loading : SimilarMoviesState()
        data class Success(val similarMoviesResponse: SimilarMoviesResponse) : SimilarMoviesState()
        data class Error(val error: Throwable) : SimilarMoviesState()
    }
}
