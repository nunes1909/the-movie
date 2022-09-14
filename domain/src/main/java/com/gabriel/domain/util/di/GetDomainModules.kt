package com.gabriel.domain.util.di

import com.gabriel.domain.movie.useCase.*
import com.gabriel.domain.movie.useCaseImpl.*
import org.koin.dsl.module

fun getDomainModules() = module {
    // Region Movies modules
    factory<GetAllMoviesUseCase> { GetAllMoviesUseCaseImpl(repository = get()) }

    factory<GetDetailMovieUseCase> { GetDetailMovieUseCaseImpl(repository = get()) }

    factory<GetSimilarMoviesUseCase> { GetSimilarMoviesUseCaseImpl(repository = get()) }

    factory<GetRecentMovieUseCase> { GetRecentMovieUseCaseImpl(repository = get()) }
    // Endregion

    // Multi modules
    factory<SearchMovieUseCase> { SearchMovieUseCaseImpl(repository = get()) }
}
