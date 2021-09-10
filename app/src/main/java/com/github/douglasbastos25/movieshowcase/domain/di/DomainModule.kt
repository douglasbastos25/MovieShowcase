package com.github.douglasbastos25.movieshowcase.domain.di

import com.github.douglasbastos25.movieshowcase.domain.GetConfigurationUseCase
import com.github.douglasbastos25.movieshowcase.domain.GetGenresUseCase
import com.github.douglasbastos25.movieshowcase.domain.GetMovieUseCase
import com.github.douglasbastos25.movieshowcase.domain.GetSimilarMoviesUseCase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {

    fun load(){
        loadKoinModules(useCaseModule())
    }

    private fun useCaseModule(): Module {
        return module {
            factory {
                GetMovieUseCase(get())
            }
            factory {
                GetGenresUseCase(get())
            }
            factory {
                GetSimilarMoviesUseCase(get())
            }
            factory {
                GetConfigurationUseCase(get())
            }
        }
    }
}
