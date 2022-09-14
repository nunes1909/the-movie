package com.gabriel.domain.util.di

import com.gabriel.domain.movie.repository.GetAllMoviesRepository
import com.gabriel.domain.movie.repository.GetDetailMovieRepository
import com.gabriel.domain.movie.repository.GetRecentMovieRepository
import com.gabriel.domain.movie.repository.GetSimilarMoviesRepository
import com.gabriel.domain.movie.useCase.GetAllMoviesUseCase
import com.gabriel.domain.movie.useCase.GetDetailMovieUseCase
import com.gabriel.domain.movie.useCase.GetRecentMovieUseCase
import com.gabriel.domain.movie.useCase.GetSimilarMoviesUseCase
import com.gabriel.domain.movie.useCaseImpl.GetAllMoviesUseCaseImpl
import com.gabriel.domain.movie.useCaseImpl.GetDetailMovieUseCaseImpl
import com.gabriel.domain.movie.useCaseImpl.GetRecentMovieUseCaseImpl
import com.gabriel.domain.movie.useCaseImpl.GetSimilarMoviesUseCaseImpl
import com.gabriel.domain.movie.repository.SearchMovieRepository
import com.gabriel.domain.movie.useCase.SearchMovieUseCase
import com.gabriel.domain.movie.useCaseImpl.SearchMovieUseCaseImpl
import org.koin.dsl.module

fun getDomainModules() = module {
    // Region Movies modules
    factory<GetAllMoviesRepository> { get() }
    factory<GetAllMoviesUseCase> { GetAllMoviesUseCaseImpl(repository = get()) }

    factory<GetDetailMovieRepository> { get() }
    factory<GetDetailMovieUseCase> { GetDetailMovieUseCaseImpl(repository = get()) }

    factory<GetSimilarMoviesRepository> { get() }
    factory<GetSimilarMoviesUseCase> { GetSimilarMoviesUseCaseImpl(repository = get()) }

    factory<GetRecentMovieRepository> { get() }
    factory<GetRecentMovieUseCase> { GetRecentMovieUseCaseImpl(repository = get()) }
    // Endregion

    // Multi modules
    factory<SearchMovieRepository> { get() }
    factory<SearchMovieUseCase> { SearchMovieUseCaseImpl(repository = get()) }
}
