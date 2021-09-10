package com.github.douglasbastos25.movieshowcase.data.services

import com.github.douglasbastos25.movieshowcase.data.model.Movie
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBService {
    @GET("movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") id: Int, @Query("api_key") apiKey: String): Movie
}