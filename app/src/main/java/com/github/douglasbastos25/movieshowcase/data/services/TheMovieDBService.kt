package com.github.douglasbastos25.movieshowcase.data.services

import com.github.douglasbastos25.movieshowcase.data.model.Configuration
import com.github.douglasbastos25.movieshowcase.data.model.GenresResponse
import com.github.douglasbastos25.movieshowcase.data.model.Movie
import com.github.douglasbastos25.movieshowcase.data.model.SimilarMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface TheMovieDBService {
    @GET("movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") id: Int): Movie

    @GET("genre/movie/list")
    suspend fun getGenres(): GenresResponse

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") id: Int
    ): SimilarMoviesResponse

    @GET("configuration")
    suspend fun getConfiguration(): Configuration
}
