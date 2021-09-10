package com.github.douglasbastos25.movieshowcase.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.github.douglasbastos25.movieshowcase.preferences.SharedPreferencesLike
import com.github.douglasbastos25.movieshowcase.preferences.SharedPreferencesLike.Companion.LIKE_VALUE
import com.github.douglasbastos25.movieshowcase.databinding.ActivityMainBinding
import com.github.douglasbastos25.movieshowcase.presentation.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var preferencesLike: SharedPreferencesLike
    private val viewModel by viewModel<MainViewModel>()

    companion object{
        const val MOVIE_ID = 38
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getMovie()
        loadContent()
        setListeners()
    }

    private fun getMovie() {
        viewModel.getMovie(MOVIE_ID)
        viewModel.movie.observe(this){
            when(it){
                is MainViewModel.MovieState.Error -> {
                    Log.e("Error: ", it.error.message.toString())
                }
                is MainViewModel.MovieState.Success -> {
                    binding.tvTitle.text = it.movie.title
                    Log.e("Movie Name ", it.movie.title)
                    binding.tvLikes.text = it.movie.likes.toString()
                    binding.tvViews.text = it.movie.view.toString()
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