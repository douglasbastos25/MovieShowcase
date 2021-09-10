package com.github.douglasbastos25.movieshowcase.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.douglasbastos25.movieshowcase.data.model.Movie
import com.github.douglasbastos25.movieshowcase.domain.GetMovieUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel (
    private val getMovieUseCase: GetMovieUseCase
): ViewModel(){

    private val _movie = MutableLiveData<MovieState>()
    val movie: LiveData<MovieState> = _movie

    fun getMovie(id: Int){
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


    sealed class MovieState{
        object Loading: MovieState()
        data class Success(val movie: Movie): MovieState()
        data class Error(val error: Throwable): MovieState()
    }
}