package com.github.douglasbastos25.movieshowcase.domain.di

import com.github.douglasbastos25.movieshowcase.domain.GetMovieUseCase
import get
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
        }
    }
}