package com.gabriel.remote.util.di

import com.gabriel.data.features.filme.dataSource.GetFilmesDataSource
import com.gabriel.data.features.filme.dataSource.GetFilmesSimilaresDataSource
import com.gabriel.data.features.filme.dataSource.GetTrandingFilmesDataSource
import com.gabriel.data.features.multiSearch.dataSource.MultiSearchDataSource
import com.gabriel.data.features.serie.dataSource.GetSeriesDataSource
import com.gabriel.data.features.serie.dataSource.GetSeriesSimilaresDataSource
import com.gabriel.data.features.serie.dataSource.GetTrandingSeriesDataSource
import com.gabriel.remote.features.filme.dataSource.GetFilmesDataSourceImpl
import com.gabriel.remote.features.filme.dataSource.GetFilmesSimilaresDataSourceImpl
import com.gabriel.remote.features.filme.dataSource.GetTrandingFilmesDataSourceImpl
import com.gabriel.remote.features.filme.mapper.FilmeDetailRemoteMapper
import com.gabriel.remote.features.filme.mapper.FilmeRemoteMapper
import com.gabriel.remote.features.filme.service.FilmesService
import com.gabriel.remote.features.genero.mapper.GeneroRemoteMapper
import com.gabriel.remote.features.multiSearch.dataSource.MultiSearchDataSourceImpl
import com.gabriel.remote.features.multiSearch.mapper.MultiRemoteMapper
import com.gabriel.remote.features.multiSearch.service.MultiService
import com.gabriel.remote.features.serie.dataSource.GetSeriesDataSourceImpl
import com.gabriel.remote.features.serie.dataSource.GetSeriesSimilaresDataSourceImpl
import com.gabriel.remote.features.serie.dataSource.GetTrandingSeriesDataSourceImpl
import com.gabriel.remote.features.serie.mapper.SerieDetailRemoteMapper
import com.gabriel.remote.features.serie.mapper.SerieRemoteMapper
import com.gabriel.remote.features.serie.service.SeriesService
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

    // Filmes Modules
    factory { FilmeRemoteMapper() }
    factory { FilmeDetailRemoteMapper(mapper = get()) }
    single<GetFilmesDataSource> {
        GetFilmesDataSourceImpl(api = get(), mapperFilmes = get(), mapperDetail = get())
    }

    single<GetFilmesSimilaresDataSource> {
        GetFilmesSimilaresDataSourceImpl(api = get(), mapperFilmes = get())
    }

    single<GetTrandingFilmesDataSource> {
        GetTrandingFilmesDataSourceImpl(api = get(), mapper = get())
    }

    // Series Modules
    factory { SerieRemoteMapper() }
    factory { SerieDetailRemoteMapper(mapper = get()) }
    single<GetSeriesDataSource> {
        GetSeriesDataSourceImpl(api = get(), mapperSerie = get(), detailMapper = get())
    }

    single<GetSeriesSimilaresDataSource> {
        GetSeriesSimilaresDataSourceImpl(api = get(), mapperSeries = get())
    }

    single<GetTrandingSeriesDataSource> {
        GetTrandingSeriesDataSourceImpl(api = get(), mapper = get())
    }

    // Multi modules
    single<MultiService> { get() }
    factory { MultiRemoteMapper(mapper = get()) }
    single<MultiSearchDataSource> {MultiSearchDataSourceImpl(api = get(), mapper = get())}
}
