package com.gabriel.domain.util.di

import com.gabriel.domain.features.filme.repository.GetFilmesRepository
import com.gabriel.domain.features.filme.useCase.GetFilmesUseCase
import com.gabriel.domain.features.filme.useCaseImpl.GetFilmesUseCaseImpl
import com.gabriel.domain.features.multiSearch.repository.MultiSearchRepository
import com.gabriel.domain.features.multiSearch.useCase.SearchMultiUseCase
import com.gabriel.domain.features.multiSearch.useCaseImpl.SearchMultiUseCaseImpl
import com.gabriel.domain.features.serie.repository.GetSeriesRepository
import com.gabriel.domain.features.serie.useCase.GetSeriesUseCase
import com.gabriel.domain.features.serie.useCaseImpl.GetSeriesUseCaseImpl
import org.koin.dsl.module

fun getDomainModules() = module {
    // Filmes modules
    factory<GetFilmesRepository> { get() }
    factory<GetFilmesUseCase> { GetFilmesUseCaseImpl(get()) }

    // Series modules
    factory<GetSeriesRepository> { get() }
    factory<GetSeriesUseCase> { GetSeriesUseCaseImpl(get()) }

    // Multi modules
    factory<MultiSearchRepository> { get() }
    factory<SearchMultiUseCase> { SearchMultiUseCaseImpl(get()) }
}
