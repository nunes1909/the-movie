package com.gabriel.remote.util.di

import com.gabriel.data.features.filme.dataSource.GetFilmesDataSource
import com.gabriel.data.features.multiSearch.dataSource.MultiSearchDataSource
import com.gabriel.data.features.serie.dataSource.GetSeriesDataSource
import com.gabriel.remote.features.filme.dataSource.GetFilmesDataSourceImpl
import com.gabriel.remote.features.filme.mapper.FilmeDetailRemoteMapper
import com.gabriel.remote.features.filme.mapper.FilmeRemoteMapper
import com.gabriel.remote.features.filme.service.FilmesService
import com.gabriel.remote.features.genero.mapper.GeneroRemoteMapper
import com.gabriel.remote.features.multiSearch.dataSource.MultiSearchDataSourceImpl
import com.gabriel.remote.features.multiSearch.mapper.MultiRemoteMapper
import com.gabriel.remote.features.multiSearch.service.MultiService
import com.gabriel.remote.features.serie.dataSource.GetSeriesDataSourceImpl
import com.gabriel.remote.features.serie.mapper.SerieDetailRemoteMapper
import com.gabriel.remote.features.serie.mapper.SerieRemoteMapper
import com.gabriel.remote.features.serie.service.SeriesService
import com.gabriel.remote.network.retrofit.TheMovieRetrofit
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

fun getRemoteModules() = module {
    // Retrofit Modules
    single { TheMovieRetrofit().getOkHttpClient() }
    single<Retrofit> { TheMovieRetrofit().getRetrofit(client = get()) }
    single(named("FilmesService")) { TheMovieRetrofit().getFilmesService(retrofit = get()) }
    single(named("SeriesService")) { TheMovieRetrofit().getSeriesService(retrofit = get()) }

    // Genero Modules
    factory { GeneroRemoteMapper() }

    // Filmes Modules
    single<FilmesService> { get() }
    factory { FilmeRemoteMapper() }
    factory { FilmeDetailRemoteMapper(mapper = get()) }
    single<GetFilmesDataSource> {
        GetFilmesDataSourceImpl(api = get(), mapperFilmes = get(), mapperDetail = get())
    }

    // Series Modules
    single<SeriesService> { get() }
    factory { SerieRemoteMapper() }
    factory { SerieDetailRemoteMapper(mapper = get()) }
    single<GetSeriesDataSource> {
        GetSeriesDataSourceImpl(api = get(), mapperSerie = get(), detailMapper = get())
    }

    // Multi modules
    single<MultiService> { get() }
    factory { MultiRemoteMapper(mapper = get()) }
    single<MultiSearchDataSource> {MultiSearchDataSourceImpl(api = get(), mapper = get())}
}
