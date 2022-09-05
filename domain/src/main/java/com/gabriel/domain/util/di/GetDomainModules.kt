package com.gabriel.domain.util.di

import com.gabriel.domain.features.filme.repository.GetFilmesRepository
import com.gabriel.domain.features.filme.useCase.GetFilmesUseCase
import com.gabriel.domain.features.filme.useCaseImpl.GetFilmesUseCaseImpl
import com.gabriel.domain.features.serie.repository.GetSeriesRepository
import com.gabriel.domain.features.serie.useCase.GetSeriesUseCase
import com.gabriel.domain.features.serie.useCaseImpl.GetSeriesUseCaseImpl
import org.koin.dsl.module

fun getDomainModules() = module {
    factory<GetFilmesRepository> { get() }
    factory<GetFilmesUseCase> { GetFilmesUseCaseImpl(get()) }

    factory<GetSeriesRepository> { get() }
    factory<GetSeriesUseCase> { GetSeriesUseCaseImpl(get()) }
}