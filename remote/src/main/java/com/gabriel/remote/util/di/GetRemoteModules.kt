package com.gabriel.remote.util.di

import com.gabriel.data.movie.dataSource.filme.GetAllFilmesDataSource
import com.gabriel.data.movie.dataSource.filme.GetDetailFilmeDataSource
import com.gabriel.data.movie.dataSource.filme.GetTrendingFilmeDataSource
import com.gabriel.data.movie.dataSource.filme.GetSimilarFilmesDataSource
import com.gabriel.data.movie.dataSource.movie.SearchMovieDataSource
import com.gabriel.data.movie.dataSource.serie.*
import com.gabriel.remote.genero.mapper.GeneroRemoteMapper
import com.gabriel.remote.movie.dataSourceImpl.filme.GetAllFilmesDataSourceImpl
import com.gabriel.remote.movie.dataSourceImpl.filme.GetDetailFilmeDataSourceImpl
import com.gabriel.remote.movie.dataSourceImpl.filme.GetTrendingFilmeDataSourceImpl
import com.gabriel.remote.movie.dataSourceImpl.filme.GetSimilarFilmesDataSourceImpl
import com.gabriel.remote.movie.dataSourceImpl.multi.SearchMovieDataSourceImpl
import com.gabriel.remote.movie.dataSourceImpl.serie.*
import com.gabriel.remote.movie.mapper.filme.FilmeDetailResponseToDataMapper
import com.gabriel.remote.movie.mapper.filme.FilmeResponseToDataMapper
import com.gabriel.remote.movie.mapper.multi.MultiRemoteToMovieMapper
import com.gabriel.remote.movie.mapper.serie.SerieDetailResponseToDataMapper
import com.gabriel.remote.movie.mapper.serie.SerieResponseToDataMapper
import com.gabriel.remote.movie.service.filme.FilmesService
import com.gabriel.remote.movie.service.multi.MultiService
import com.gabriel.remote.movie.service.serie.SeriesService
import com.gabriel.remote.movie.service.trending.TrendingService
import com.gabriel.remote.network.retrofit.TheMovieRetrofit
import org.koin.dsl.module
import retrofit2.Retrofit

fun getRemoteModules() = module {
    // Retrofit Modules
    single { TheMovieRetrofit().getOkHttpClient() }
    single<Retrofit> { TheMovieRetrofit().getRetrofit(client = get()) }
    single<FilmesService> { TheMovieRetrofit().getFilmesService(retrofit = get()) }
    single<SeriesService> { TheMovieRetrofit().getSeriesService(retrofit = get()) }
    single<MultiService> { TheMovieRetrofit().getMultiService(retrofit = get()) }
    single<TrendingService> { TheMovieRetrofit().getTrendingService(retrofit = get()) }

    // Genero Modules
    factory { GeneroRemoteMapper() }

    // Region Movie modules
    factory { FilmeDetailResponseToDataMapper(mapper = get()) }
    factory { FilmeResponseToDataMapper() }
    factory { SerieDetailResponseToDataMapper() }
    factory { SerieResponseToDataMapper() }
    factory { MultiRemoteToMovieMapper() }

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
    single<GetTrendingFilmeDataSource> {
        GetTrendingFilmeDataSourceImpl(
            service = get(),
            mapper = get()
        )
    }
    single<GetTrendingSerieDataSource> {
        GetTrendingSerieDataSourceImpl(
            service = get(),
            mapper = get()
        )
    }
    single<GetRecentSerieDataSource> {
        GetRecentSerieDataSourceImpl(
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
    single<SearchMovieDataSource> {
        SearchMovieDataSourceImpl(
            service = get(),
            mapper = get()
        )
    }
    single<GetSimilarFilmesDataSource> {
        GetSimilarFilmesDataSourceImpl(
            service = get(),
            mapper = get()
        )
    }
    single<GetSimilarSeriesDataSource> {
        GetSimilarSeriesDataSourceImpl(
            service = get(),
            mapper = get()
        )
    }
    // Endregion
}
