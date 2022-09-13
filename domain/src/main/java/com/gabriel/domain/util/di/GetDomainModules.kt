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
import com.gabriel.domain.features.movie.repository.GetAllMoviesRepository
import com.gabriel.domain.features.movie.repository.GetDetailMovieRepository
import com.gabriel.domain.features.movie.repository.GetRecentMovieRepository
import com.gabriel.domain.features.movie.repository.GetSimilarMoviesRepository
import com.gabriel.domain.features.movie.useCase.GetAllMoviesUseCase
import com.gabriel.domain.features.movie.useCase.GetDetailMovieUseCase
import com.gabriel.domain.features.movie.useCase.GetRecentMovieUseCase
import com.gabriel.domain.features.movie.useCase.GetSimilarMoviesUseCase
import com.gabriel.domain.features.movie.useCaseImpl.GetAllMoviesUseCaseImpl
import com.gabriel.domain.features.movie.useCaseImpl.GetDetailMovieUseCaseImpl
import com.gabriel.domain.features.movie.useCaseImpl.GetRecentMovieUseCaseImpl
import com.gabriel.domain.features.movie.useCaseImpl.GetSimilarMoviesUseCaseImpl
import com.gabriel.domain.features.movie.repository.SearchMovieRepository
import com.gabriel.domain.features.movie.useCase.SearchMovieUseCase
import com.gabriel.domain.features.movie.useCaseImpl.SearchMovieUseCaseImpl
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
    factory<SearchMovieRepository> { get() }
    factory<SearchMovieUseCase> { SearchMovieUseCaseImpl(repository = get()) }
}
