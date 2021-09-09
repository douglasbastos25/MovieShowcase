package com.github.douglasbastos25.movieshowcase.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.douglasbastos25.movieshowcase.preferences.SharedPreferencesLike
import com.github.douglasbastos25.movieshowcase.preferences.SharedPreferencesLike.Companion.LIKE_VALUE
import com.github.douglasbastos25.movieshowcase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var preferencesLike: SharedPreferencesLike


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        loadContent()
        setListeners()
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