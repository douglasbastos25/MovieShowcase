package com.github.douglasbastos25.movieshowcase.helpers

import android.app.Application
import androidx.test.runner.AndroidJUnitRunner
import com.github.douglasbastos25.movieshowcase.data.services.TheMovieDBService
import okhttp3.OkHttpClient
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

class CustomTestRunner : AndroidJUnitRunner() {
    companion object {
        const val MOCK_WEB_SERVER_PORT = 4007
    }

    override fun callApplicationOnCreate(app: Application?) {
        super.callApplicationOnCreate(app)
        loadKoinModules(module {
            single(override = true) { createTestWebService<TheMovieDBService>(get()) }
        })
    }

    private inline fun <reified T> createTestWebService(okHttpClient: OkHttpClient): T {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://localhost:$MOCK_WEB_SERVER_PORT/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
        return retrofit.create(T::class.java)
    }
}
