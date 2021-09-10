package com.github.douglasbastos25.movieshowcase.data.di

import com.github.douglasbastos25.movieshowcase.core.APIKeyInterceptor
import com.github.douglasbastos25.movieshowcase.data.repositories.*
import com.github.douglasbastos25.movieshowcase.data.services.TheMovieDBService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModule {

    fun load(){
        loadKoinModules(networkModules() + repositoryModule())
    }

    private fun networkModules(): Module {
        return module {
            single {
                val httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

                OkHttpClient.Builder()
                    .addInterceptor(APIKeyInterceptor())
                    .addInterceptor(httpLoggingInterceptor)
                    .build()
            }

            single {
                GsonConverterFactory.create(GsonBuilder().create())
            }

            single {
                createService<TheMovieDBService>(get(), get())
            }
        }
    }

    private fun repositoryModule(): Module {
        return module {
            single<MovieRepository> {
                MovieRepositoryImpl(get())
            }
            single<GenreRepository> {
                GenreRepositoryImpl(get())
            }
            single <SimilarMoviesRepository> {
                SimilarMoviesRepositoryImpl(get())
            }
        }
    }

    private inline fun <reified T> createService(client: OkHttpClient, factory: GsonConverterFactory): T{
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(client)
            .addConverterFactory(factory)
            .build().create(T::class.java)
    }
}