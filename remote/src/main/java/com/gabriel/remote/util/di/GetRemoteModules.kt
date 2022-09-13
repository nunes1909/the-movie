package com.gabriel.remote.util.di

import com.gabriel.data.features.movie.dataSource.filme.GetAllFilmesDataSource
import com.gabriel.data.features.movie.dataSource.filme.GetDetailFilmeDataSource
import com.gabriel.data.features.movie.dataSource.serie.GetAllSeriesDataSource
import com.gabriel.data.features.movie.dataSource.serie.GetDetailSerieDataSource
import com.gabriel.data.features.movie.dataSource.movie.SearchMovieDataSource
import com.gabriel.remote.features.genero.mapper.GeneroRemoteMapper
import com.gabriel.remote.features.movie.dataSourceImpl.filme.GetAllFilmesDataSourceImpl
import com.gabriel.remote.features.movie.dataSourceImpl.filme.GetDetailFilmeDataSourceImpl
import com.gabriel.remote.features.movie.dataSourceImpl.multi.SearchMovieDataSourceImpl
import com.gabriel.remote.features.movie.dataSourceImpl.serie.GetAllSeriesDataSourceImpl
import com.gabriel.remote.features.movie.dataSourceImpl.serie.GetDetailSerieDataSourceImpl
import com.gabriel.remote.features.movie.mapper.filme.FilmeDetailResponseToDataMapper
import com.gabriel.remote.features.movie.mapper.filme.FilmeResponseToDataMapper
import com.gabriel.remote.features.movie.mapper.multi.MultiRemoteToMovieMapper
import com.gabriel.remote.features.movie.mapper.serie.SerieDetailResponseToDataMapper
import com.gabriel.remote.features.movie.mapper.serie.SerieResponseToDataMapper
import com.gabriel.remote.features.movie.service.filme.FilmesService
import com.gabriel.remote.features.movie.service.multi.MultiService
import com.gabriel.remote.features.movie.service.serie.SeriesService
import com.gabriel.remote.network.retrofit.TheMovieRetrofit
import org.koin.dsl.module
import retrofit2.Retrofit

fun getRemoteModules() = module {
    // Retrofit Modules
    single { TheMovieRetrofit().getOkHttpClient() }
    single<Retrofit> { TheMovieRetrofit().getRetrofit(client = get()) }
    single<FilmesService> { TheMovieRetrofit().getFilmesService(retrofit = get()) }
    single<SeriesService> { TheMovieRetrofit().getSeriesService(retrofit = get()) }

    // Genero Modules
    factory { GeneroRemoteMapper() }

    // Region Movie modules
    factory { FilmeResponseToDataMapper() }
    factory { FilmeDetailResponseToDataMapper() }
    factory { SerieResponseToDataMapper() }
    factory { SerieDetailResponseToDataMapper() }
    single<GetAllFilmesDataSource> {
        GetAllFilmesDataSourceImpl(
            service = get(),
            mapper = get()
        )
    }
    single<GetDetailFilmeDataSource> {
        GetDetailFilmeDataSourceImpl(
            service = get(),
            mapper = get()
        )
    }
    single<GetAllSeriesDataSource> {
        GetAllSeriesDataSourceImpl(
            service = get(),
            mapper = get()
        )
    }
    single<GetDetailSerieDataSource> {
        GetDetailSerieDataSourceImpl(
            service = get(),
            mapper = get()
        )
    }
    // Endregion

    // Multi modules
    single<MultiService> { get() }
    factory { MultiRemoteToMovieMapper(mapper = get()) }
    single<SearchMovieDataSource> { SearchMovieDataSourceImpl(api = get(), mapper = get()) }
}
