package com.gabriel.data.util.di

import com.gabriel.data.filme.dataSource.GetFilmesDataSource
import com.gabriel.data.filme.mapper.FilmeDataMapper
import com.gabriel.data.filme.repository.GetFilmesRepositoryImpl
import com.gabriel.data.genero.mapper.GeneroDataMapper
import com.gabriel.data.serie.dataSource.GetSeriesDataSource
import com.gabriel.data.serie.mapper.SerieDataMapper
import com.gabriel.data.serie.repository.GetSeriesRepositoryImpl
import com.gabriel.domain.filme.repository.GetFilmesRepository
import com.gabriel.domain.serie.repository.GetSeriesRepository
import org.koin.dsl.module

fun getDataModules() = module {
    // Genero modules
    factory { GeneroDataMapper() }

    // Filmes modules
    factory<GetFilmesDataSource> { get() }
    factory { FilmeDataMapper(get()) }
    factory<GetFilmesRepository> { GetFilmesRepositoryImpl(get(), get()) }

    // Series modules
    factory<GetSeriesDataSource> { get() }
    factory { SerieDataMapper(get()) }
    factory<GetSeriesRepository> { GetSeriesRepositoryImpl(get(), get()) }
}
