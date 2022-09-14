package com.gabriel.data.util.di

import com.gabriel.data.genero.mapper.GeneroDataMapper
import com.gabriel.data.movie.dataSource.filme.GetAllFilmesDataSource
import com.gabriel.data.movie.dataSource.filme.GetDetailFilmeDataSource
import com.gabriel.data.movie.dataSource.filme.GetSimilarFilmesDataSource
import com.gabriel.data.movie.dataSource.movie.SearchMovieDataSource
import com.gabriel.data.movie.dataSource.serie.GetAllSeriesDataSource
import com.gabriel.data.movie.dataSource.serie.GetDetailSerieDataSource
import com.gabriel.data.movie.dataSource.serie.GetSimilarSeriesDataSource
import com.gabriel.data.movie.dataStore.GetAllMoviesDataStore
import com.gabriel.data.movie.dataStore.GetDetailMovieDataStore
import com.gabriel.data.movie.dataStore.GetSimilarMoviesDataStore
import com.gabriel.data.movie.dataStore.SearchMovieDataStore
import com.gabriel.data.movie.dataStoreImpl.GetAllMoviesDataStoreImpl
import com.gabriel.data.movie.dataStoreImpl.GetDetailMovieDataStoreImpl
import com.gabriel.data.movie.dataStoreImpl.GetSimilarMoviesDataStoreImpl
import com.gabriel.data.movie.dataStoreImpl.SearchMovieDataStoreImpl
import com.gabriel.data.movie.mapper.MovieDataMapper
import com.gabriel.data.movie.repository.GetAllMoviesRepositoryImpl
import com.gabriel.data.movie.repository.GetDetailMovieRepositoryImpl
import com.gabriel.data.movie.repository.GetSimilarMoviesRepositoryImpl
import com.gabriel.data.movie.repository.SearchMovieRepositoryImpl
import com.gabriel.domain.movie.repository.GetAllMoviesRepository
import com.gabriel.domain.movie.repository.GetDetailMovieRepository
import com.gabriel.domain.movie.repository.GetSimilarMoviesRepository
import com.gabriel.domain.movie.repository.SearchMovieRepository
import org.koin.dsl.module

fun getDataModules() = module {
    // Genero modules
    factory { GeneroDataMapper() }

    // Region Movie modules
    factory { MovieDataMapper() }

    factory<GetAllFilmesDataSource> { get() }
    factory<GetDetailFilmeDataSource> { get() }
    factory<GetSimilarFilmesDataSource> { get() }
    factory<GetAllSeriesDataSource> { get() }
    factory<GetDetailSerieDataSource> { get() }
    factory<SearchMovieDataSource> { get() }
    factory<GetSimilarSeriesDataSource> { get() }

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

    // Multi modules
    factory<SearchMovieDataSource> { get() }
}
