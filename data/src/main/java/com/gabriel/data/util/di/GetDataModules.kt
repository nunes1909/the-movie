package com.gabriel.data.util.di

import com.gabriel.data.features.filme.dataSource.GetFilmesDataSource
import com.gabriel.data.features.filme.dataSource.GetFilmesSimilaresDataSource
import com.gabriel.data.features.filme.dataSource.GetTrandingFilmesDataSource
import com.gabriel.data.features.filme.mapper.FilmeDataMapper
import com.gabriel.data.features.filme.repository.GetFilmesRepositoryImpl
import com.gabriel.data.features.filme.repository.GetFilmesSimilaresRepositoryImpl
import com.gabriel.data.features.filme.repository.GetTrandingFilmesRepositoryImpl
import com.gabriel.data.features.genero.mapper.GeneroDataMapper
import com.gabriel.data.features.movie.datasource.GetAllMoviesDataSource
import com.gabriel.data.features.movie.datasource.GetDetailMovieDataSource
import com.gabriel.data.features.movie.datasource.GetRecentMovieDataSource
import com.gabriel.data.features.movie.datasource.GetSimilarMoviesDataSource
import com.gabriel.data.features.movie.mapper.MovieDataMapper
import com.gabriel.data.features.movie.repository.GetAllMoviesRepositoryImpl
import com.gabriel.data.features.movie.repository.GetDetailMovieRepositoryImpl
import com.gabriel.data.features.movie.repository.GetRecentMovieRepositoryImpl
import com.gabriel.data.features.movie.repository.GetSimilarMoviesRepositoryImpl
import com.gabriel.data.features.multiSearch.dataSource.MultiSearchDataSource
import com.gabriel.data.features.multiSearch.mapper.MultiDataMapper
import com.gabriel.data.features.multiSearch.repository.MultiSearchRepositoryImpl
import com.gabriel.data.features.serie.dataSource.GetSeriesDataSource
import com.gabriel.data.features.serie.dataSource.GetSeriesSimilaresDataSource
import com.gabriel.data.features.serie.dataSource.GetTrandingSeriesDataSource
import com.gabriel.data.features.serie.mapper.SerieDataMapper
import com.gabriel.data.features.serie.repository.GetSeriesRepositoryImpl
import com.gabriel.data.features.serie.repository.GetSeriesSimilaresRepositoryImpl
import com.gabriel.data.features.serie.repository.GetTrandingSeriesRepositoryImpl
import com.gabriel.domain.features.filme.repository.GetFilmesRepository
import com.gabriel.domain.features.filme.repository.GetFilmesSimilaresRepository
import com.gabriel.domain.features.filme.repository.GetTrandingFilmesRepository
import com.gabriel.domain.features.movie.repository.GetAllMoviesRepository
import com.gabriel.domain.features.movie.repository.GetDetailMovieRepository
import com.gabriel.domain.features.movie.repository.GetRecentMovieRepository
import com.gabriel.domain.features.movie.repository.GetSimilarMoviesRepository
import com.gabriel.domain.features.multiSearch.repository.MultiSearchRepository
import com.gabriel.domain.features.serie.repository.GetSeriesRepository
import com.gabriel.domain.features.serie.repository.GetSeriesSimilaresRepository
import com.gabriel.domain.features.serie.repository.GetTrandingSeriesRepository
import org.koin.dsl.module

fun getDataModules() = module {
    // Genero modules
    factory { GeneroDataMapper() }

    // Region Movie modules
    factory { MovieDataMapper() }

    factory<GetAllMoviesDataSource> { get() }
    factory<GetDetailMovieDataSource> { get() }
    factory<GetRecentMovieDataSource> { get() }
    factory<GetSimilarMoviesDataSource> { get() }

    factory<GetAllMoviesRepository> {
        GetAllMoviesRepositoryImpl(dataSource = get(),mapper = get())
    }
    factory<GetDetailMovieRepository> {
        GetDetailMovieRepositoryImpl(dataSource = get(),mapper = get())
    }
    factory<GetRecentMovieRepository> {
        GetRecentMovieRepositoryImpl(dataSource = get(),mapper = get())
    }
    factory<GetSimilarMoviesRepository> {
        GetSimilarMoviesRepositoryImpl(dataSource = get(),mapper = get())
    }
    // Endregion

    // Filmes modules
    factory<GetFilmesDataSource> { get() }
    factory { FilmeDataMapper(mapper = get()) }
    factory<GetFilmesRepository> { GetFilmesRepositoryImpl(dataSource = get(), mapper = get()) }

    factory<GetFilmesSimilaresDataSource> { get() }
    factory<GetFilmesSimilaresRepository> {
        GetFilmesSimilaresRepositoryImpl(dataSource = get(), mapper = get())
    }

    factory<GetTrandingFilmesDataSource> { get() }
    factory<GetTrandingFilmesRepository> {
        GetTrandingFilmesRepositoryImpl(
            dataSource = get(),
            mapper = get()
        )
    }

    // Series modules
    factory<GetSeriesDataSource> { get() }
    factory { SerieDataMapper(mapper = get()) }
    factory<GetSeriesRepository> { GetSeriesRepositoryImpl(dataSource = get(), mapper = get()) }

    factory<GetSeriesSimilaresDataSource> { get() }
    factory<GetSeriesSimilaresRepository> {
        GetSeriesSimilaresRepositoryImpl(dataSource = get(), mapper = get())
    }

    factory<GetTrandingSeriesDataSource> { get() }
    factory<GetTrandingSeriesRepository> {
        GetTrandingSeriesRepositoryImpl(
            dataSource = get(),
            mapper = get()
        )
    }

    // Multi modules
    factory<MultiSearchDataSource> { get() }
    factory { MultiDataMapper(mapper = get()) }
    factory<MultiSearchRepository> { MultiSearchRepositoryImpl(dataSource = get(), mapper = get()) }
}
