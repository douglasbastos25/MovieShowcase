package com.github.douglasbastos25.movieshowcase.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.github.douglasbastos25.movieshowcase.data.model.Genre
import com.github.douglasbastos25.movieshowcase.preferences.SharedPreferencesLike
import com.github.douglasbastos25.movieshowcase.preferences.SharedPreferencesLike.Companion.LIKE_VALUE
import com.github.douglasbastos25.movieshowcase.databinding.ActivityMainBinding
import com.github.douglasbastos25.movieshowcase.presentation.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy{ActivityMainBinding.inflate(layoutInflater)}
    private lateinit var preferencesLike: SharedPreferencesLike
    private val viewModel by viewModel<MainViewModel>()
    private var genresList: List<Genre> = listOf()
    private val adapter by lazy {SimilarMoviesListAdapter()}

    companion object{
        const val MOVIE_ID = 38
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvSimilarMovies.adapter = adapter

        getMovie()
        loadContent()
        setListeners()
    }

    private fun getMovie() {
        viewModel.getMovie(MOVIE_ID)
        viewModel.movie.observe(this){
            when(it){
                is MainViewModel.MovieState.Error -> {
                    Log.e("Movie Error: ", it.error.message.toString())
                }
                is MainViewModel.MovieState.Success -> {
                    binding.tvTitle.text = it.movie.title
                    binding.tvLikes.text = it.movie.likes.toString()
                    binding.tvViews.text = it.movie.views.toString()
                    getGenres()
                }
            }
        }
    }

    private fun getGenres() {
        viewModel.getGenres()
        viewModel.genresObject.observe(this){
            when(it){
                is MainViewModel.GenresState.Error -> {
                    Log.e("Genres Error: ", it.error.message.toString())
                }
                is MainViewModel.GenresState.Success -> {
                    genresList = it.genresResponse.genres
                    getSimilarMovies()
                }
            }
        }
    }

    private fun getSimilarMovies() {
        viewModel.getSimilarMovies(MOVIE_ID)
        viewModel.similarMoviesObject.observe(this){
            when(it){
                is MainViewModel.SimilarMoviesState.Error -> {
                    Log.e("Similar Movies Error: ", it.error.message.toString())
                }
                is MainViewModel.SimilarMoviesState.Success -> {
                    adapter.submitList(it.similarMoviesResponse.results)
                }
            }
        }
    }

    private fun loadContent() {
        preferencesLike = SharedPreferencesLike(this)
        binding.btLike.isSelected = preferencesLike.get(LIKE_VALUE)
    }

    private fun setListeners() {
        binding.btLike.setOnClickListener {
            binding.btLike.isSelected = preferencesLike.toggle(LIKE_VALUE)
        }
    }
}
