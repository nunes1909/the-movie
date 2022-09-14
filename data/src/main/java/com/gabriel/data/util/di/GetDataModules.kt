package com.gabriel.data.util.di

import com.gabriel.data.genero.mapper.GeneroDataMapper
import com.gabriel.data.movie.dataSource.filme.GetAllFilmesDataSource
import com.gabriel.data.movie.dataSource.filme.GetDetailFilmeDataSource
import com.gabriel.data.movie.dataSource.filme.GetSimilarFilmesDataSource
import com.gabriel.data.movie.dataSource.movie.SearchMovieDataSource
import com.gabriel.data.movie.dataSource.serie.GetAllSeriesDataSource
import com.gabriel.data.movie.dataSource.serie.GetDetailSerieDataSource
import com.gabriel.data.movie.dataSource.serie.GetSimilarSeriesDataSource
import com.gabriel.data.movie.dataStore.*
import com.gabriel.data.movie.dataStoreImpl.*
import com.gabriel.data.movie.mapper.MovieDataMapper
import com.gabriel.data.movie.repository.*
import com.gabriel.domain.movie.repository.*
import org.koin.dsl.module

fun getDataModules() = module {
    // Genero modules
    factory { GeneroDataMapper() }

    // Region Movie modules
    factory { MovieDataMapper(mapper = get()) }

    factory<GetAllMoviesDataStore> {
        GetAllMoviesDataStoreImpl(
            filmesSource = get(),
            seriesSource = get()
        )
    }

    factory<GetDetailMovieDataStore> {
        GetDetailMovieDataStoreImpl(
            filmesSource = get(),
            seriesService = get()
        )
    }

    factory<SearchMovieDataStore> {
        SearchMovieDataStoreImpl(
            source = get()
        )
    }

    factory<GetSimilarMoviesDataStore> {
        GetSimilarMoviesDataStoreImpl(
            filmesSource = get(),
            seriesSource = get()
        )
    }

    factory<GetRecentMovieDataStore> {
        GetRecentMovieDataStoreImpl(
            filmesSource = get(),
            seriesService = get()
        )
    }

    factory<GetAllMoviesRepository> {
        GetAllMoviesRepositoryImpl(
            dataStore = get(),
            mapper = get()
        )
    }

    factory<GetDetailMovieRepository> {
        GetDetailMovieRepositoryImpl(
            dataStore = get(),
            mapper = get()
        )
    }

    factory<GetRecentMovieRepository> {
        GetRecentMovieRepositoryImpl(
            dataStore = get(),
            mapper = get()
        )
    }

    factory<SearchMovieRepository> {
        SearchMovieRepositoryImpl(
            dataStore = get(),
            mapper = get()
        )
    }

    factory<GetSimilarMoviesRepository> {
        GetSimilarMoviesRepositoryImpl(
            dataStore = get(),
            mapper = get()
        )
    }
    // Endregion
}
