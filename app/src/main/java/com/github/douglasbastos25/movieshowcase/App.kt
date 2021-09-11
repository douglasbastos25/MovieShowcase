package com.github.douglasbastos25.movieshowcase

import android.app.Application
import com.github.douglasbastos25.movieshowcase.data.di.DataModule
import com.github.douglasbastos25.movieshowcase.domain.di.DomainModule
import com.github.douglasbastos25.movieshowcase.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@App)
        }

        DataModule.load()
        DomainModule.load()
        PresentationModule.load()
    }
}
