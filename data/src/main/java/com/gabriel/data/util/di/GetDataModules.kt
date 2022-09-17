package com.gabriel.data.util.di

import com.gabriel.data.genero.mapper.GeneroDataMapper
import com.gabriel.data.movie.dataStore.*
import com.gabriel.data.movie.dataStoreImpl.*
import com.gabriel.data.movie.mapper.MovieDataMapper
import com.gabriel.data.movie.repository.*
import com.gabriel.domain.movie.repository.*
import org.koin.dsl.module

fun getDataModules() = module {
    // Genero modules
    factory { GeneroDataMapper() }
    // Endregion

    // Region data mapper
    factory { MovieDataMapper(mapper = get()) }
    // Endregion

    // Region Data Store
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

    factory<GetTrendingMovieDataStore> {
        GetTrendingMovieDataStoreImpl(
            filmesSource = get(),
            seriesSource = get()
        )
    }

    factory<GetSimilarMoviesDataStore> {
        GetSimilarMoviesDataStoreImpl(
            filmesSource = get(),
            seriesSource = get()
        )
    }

    factory<SearchMovieDataStore> {
        SearchMovieDataStoreImpl(
            source = get()
        )
    }

    factory<SaveMovieDataStore> {
        SaveMovieDataStoreImpl(
            source = get()
        )
    }

    factory<GetFavMovieDataStore> {
        GetFavMovieDataStoreImpl(
            source = get()
        )
    }
    // Endregion

    // Region Repository
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

    factory<GetTrendingMovieRepository> {
        GetTrendingMovieRepositoryImpl(
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

    factory<SearchMovieRepository> {
        SearchMovieRepositoryImpl(
            dataStore = get(),
            mapper = get()
        )
    }

    factory<SaveMovieRepository> {
        SaveMovieRepositoryImpl(
            dataStore = get(),
            mapper = get()
        )
    }

    factory<GetFavMovieRespository> {
        GetFavMovieRespositoryImpl(
            dataStore = get(),
            mapper = get()
        )
    }
    // Endregion
}
