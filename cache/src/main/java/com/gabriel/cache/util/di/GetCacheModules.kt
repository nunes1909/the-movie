package com.gabriel.cache.util.di

import com.gabriel.cache.database.TheMovieDataBase
import com.gabriel.cache.movie.favoritos.dataSourceImpl.GetFavMovieCacheDataSourceImpl
import com.gabriel.cache.movie.favoritos.dataSourceImpl.SaveMovieCacheDataSourceImpl
import com.gabriel.cache.movie.favoritos.mapper.MovieCacheMapper
import com.gabriel.data.movie.dataSource.movie.GetFavMovieCacheDataSource
import com.gabriel.data.movie.dataSource.movie.SaveMovieCacheDataSource
import org.koin.dsl.module

fun getCacheModules() = module {
    // Region cache modules
    single { get<TheMovieDataBase>().getFavoritosDao() }
    single { TheMovieDataBase.getInstance(context = get()) }
    // Endregion

    // Region mapper
    factory { MovieCacheMapper() }
    // Endregion

    // Region Data Source
    factory<SaveMovieCacheDataSource> {
        SaveMovieCacheDataSourceImpl(
            dao = get(),
            mapper = get()
        )
    }

    factory<GetFavMovieCacheDataSource> {
        GetFavMovieCacheDataSourceImpl(
            dao = get(),
            mapper = get()
        )
    }
    // Endregion
}