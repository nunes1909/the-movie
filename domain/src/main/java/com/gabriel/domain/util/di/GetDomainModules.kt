package com.gabriel.domain.util.di

import com.gabriel.domain.filme.repository.GetFilmesRepository
import com.gabriel.domain.filme.useCase.GetFilmesUseCase
import com.gabriel.domain.filme.useCaseImpl.GetFilmesUseCaseImpl
import com.gabriel.domain.serie.repository.GetSeriesRepository
import com.gabriel.domain.serie.useCase.GetSeriesUseCase
import com.gabriel.domain.serie.useCaseImpl.GetSeriesUseCaseImpl
import org.koin.dsl.module

fun getDomainModules() = module {
    factory<GetFilmesRepository> { get() }
    factory<GetFilmesUseCase> { GetFilmesUseCaseImpl(get()) }

    factory<GetSeriesRepository> { get() }
    factory<GetSeriesUseCase> { GetSeriesUseCaseImpl(get()) }
}