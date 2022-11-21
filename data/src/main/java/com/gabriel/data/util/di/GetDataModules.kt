package com.gabriel.data.util.di

import com.gabriel.data.genero.mapper.GeneroDataMapper
import com.gabriel.data.movie.dataStore.*
import com.gabriel.data.movie.dataStoreImpl.*
import com.gabriel.data.movie.mapper.MovieDataMapper
import com.gabriel.data.movie.repository.*
import com.gabriel.data.usuario.dataStore.AutenticaUsuarioDataStore
import com.gabriel.data.usuario.dataStore.CadastraUsuarioDataStore
import com.gabriel.data.usuario.dataStoreImpl.AutenticaUsuarioDataStoreImpl
import com.gabriel.data.usuario.dataStoreImpl.CadastraUsuarioDataStoreImpl
import com.gabriel.data.usuario.mapper.UsuarioDataMapper
import com.gabriel.data.usuario.repository.AutenticaUsuarioRepositoryImpl
import com.gabriel.data.usuario.repository.CadastraUsuarioRepositoryImpl
import com.gabriel.data.video.mapper.VideoDataMapper
import com.gabriel.domain.movie.repository.*
import com.gabriel.domain.usuario.repository.AutenticaUsuarioRepository
import com.gabriel.domain.usuario.repository.CadastraUsuarioRepository
import org.koin.dsl.module

fun getDataModules() = module {
    // Genero modules
    factory { GeneroDataMapper() }
    factory { VideoDataMapper() }
    factory { UsuarioDataMapper() }
    // Endregion

    // Region data mapper
    factory {
        MovieDataMapper(
            generoMapper = get(),
            videoMapper = get()
        )
    }
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
            dataSource = get()
        )
    }

    factory<VerifyExistsMovieDataStore> {
        VerifyExistsMovieDataStoreImpl(
            source = get()
        )
    }

    factory<DeleteMovieDataStore> {
        DeleteMovieDataStoreImpl(
            source = get()
        )
    }

    factory<CadastraUsuarioDataStore> {
        CadastraUsuarioDataStoreImpl(
            dataSource = get()
        )
    }

    factory<AutenticaUsuarioDataStore> {
        AutenticaUsuarioDataStoreImpl(
            dataSource = get()
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

    factory<VerifyExistsMovieRespository> {
        VerifyExistsMovieRespositoryImpl(
            dataStore = get()
        )
    }

    factory<DeleteMovieRepository> {
        DeleteMovieRepositoryImpl(
            dataStore = get(),
            mapper = get()
        )
    }
    factory<CadastraUsuarioRepository> {
        CadastraUsuarioRepositoryImpl(
            dataStore = get(),
            mapper = get()
        )
    }
    factory<AutenticaUsuarioRepository> {
        AutenticaUsuarioRepositoryImpl(
            dataStore = get(),
            mapper = get()
        )
    }
    // Endregion
}
