package com.gabriel.domain.util.di

import com.gabriel.domain.features.filme.repository.GetFilmesRepository
import com.gabriel.domain.features.filme.repository.GetFilmesSimilaresRepository
import com.gabriel.domain.features.filme.repository.GetTrandingFilmesRepository
import com.gabriel.domain.features.filme.useCase.GetFilmesSimilaresUseCase
import com.gabriel.domain.features.filme.useCase.GetFilmesUseCase
import com.gabriel.domain.features.filme.useCase.GetTrandingFilmesUseCase
import com.gabriel.domain.features.filme.useCaseImpl.GetFilmesSimilaresUseCaseImpl
import com.gabriel.domain.features.filme.useCaseImpl.GetFilmesUseCaseImpl
import com.gabriel.domain.features.filme.useCaseImpl.GetTrandingFilmesUseCaseImpl
import com.gabriel.domain.features.multiSearch.repository.MultiSearchRepository
import com.gabriel.domain.features.multiSearch.useCase.SearchMultiUseCase
import com.gabriel.domain.features.multiSearch.useCaseImpl.SearchMultiUseCaseImpl
import com.gabriel.domain.features.serie.repository.GetSeriesRepository
import com.gabriel.domain.features.serie.repository.GetSeriesSimilaresRepository
import com.gabriel.domain.features.serie.repository.GetTrandingSeriesRepository
import com.gabriel.domain.features.serie.useCase.GetSeriesSimilaresUseCase
import com.gabriel.domain.features.serie.useCase.GetSeriesUseCase
import com.gabriel.domain.features.serie.useCase.GetTrandingSeriesUseCase
import com.gabriel.domain.features.serie.useCaseImpl.GetSeriesSimilaresUseCaseImpl
import com.gabriel.domain.features.serie.useCaseImpl.GetSeriesUseCaseImpl
import com.gabriel.domain.features.serie.useCaseImpl.GetTrandingSeriesUseCaseImpl
import org.koin.dsl.module

fun getDomainModules() = module {
    // Filmes modules
    factory<GetFilmesRepository> { get() }
    factory<GetFilmesUseCase> { GetFilmesUseCaseImpl(getFilmesRepository = get()) }

    factory<GetTrandingFilmesRepository> { get() }
    factory<GetTrandingFilmesUseCase> { GetTrandingFilmesUseCaseImpl(repository = get()) }

    factory<GetFilmesSimilaresRepository> { get() }
    factory<GetFilmesSimilaresUseCase> { GetFilmesSimilaresUseCaseImpl(repository = get()) }

    // Series modules
    factory<GetSeriesRepository> { get() }
    factory<GetSeriesUseCase> { GetSeriesUseCaseImpl(repository = get()) }

    factory<GetTrandingSeriesRepository> { get() }
    factory<GetTrandingSeriesUseCase> { GetTrandingSeriesUseCaseImpl(repository = get()) }

    factory<GetSeriesSimilaresRepository> { get() }
    factory<GetSeriesSimilaresUseCase> { GetSeriesSimilaresUseCaseImpl(repository = get()) }

    // Multi modules
    factory<MultiSearchRepository> { get() }
    factory<SearchMultiUseCase> { SearchMultiUseCaseImpl(repository = get()) }
}
