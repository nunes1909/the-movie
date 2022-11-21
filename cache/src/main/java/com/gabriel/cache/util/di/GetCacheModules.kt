package com.gabriel.cache.util.di

//import com.gabriel.cache.movie.favoritos.dataSourceImpl.SaveMovieCacheDataSourceImpl
import com.gabriel.cache.database.TheMovieDataBase
import com.gabriel.cache.movie.favoritos.dataSourceImpl.DeleteMovieDataSourceImpl
import com.gabriel.cache.movie.favoritos.dataSourceImpl.VerifyExistsMovieDataSourceImpl
import com.gabriel.cache.movie.favoritos.mapper.MovieCacheMapper
import com.gabriel.data.movie.dataSource.movie.DeleteMovieDataSource
import com.gabriel.data.movie.dataSource.movie.VerifyExistsMovieDataSource
import org.koin.dsl.module

fun getCacheModules() = module {
    // Region cache modules
    factory<TheMovieDataBase> { TheMovieDataBase.getInstance(context = get()) }
    single { get<TheMovieDataBase>().getFavoritosDao() }
    // Endregion

    // Region mapper
    factory { MovieCacheMapper() }
    // Endregion

    // Region Data Source
    factory<VerifyExistsMovieDataSource> {
        VerifyExistsMovieDataSourceImpl(
            dao = get()
        )
    }

    factory<DeleteMovieDataSource> {
        DeleteMovieDataSourceImpl(
            dao = get(),
            mapper = get()
        )
    }
    // Endregion
}