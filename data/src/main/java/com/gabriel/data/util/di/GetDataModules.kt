package com.gabriel.data.util.di

import com.gabriel.data.features.filme.dataSource.GetFilmesDataSource
import com.gabriel.data.features.filme.mapper.FilmeDataMapper
import com.gabriel.data.features.filme.repository.GetFilmesRepositoryImpl
import com.gabriel.data.features.genero.mapper.GeneroDataMapper
import com.gabriel.data.features.multiSearch.dataSource.MultiSearchDataSource
import com.gabriel.data.features.multiSearch.mapper.MultiDataMapper
import com.gabriel.data.features.multiSearch.repository.MultiSearchRepositoryImpl
import com.gabriel.data.features.serie.dataSource.GetSeriesDataSource
import com.gabriel.data.features.serie.mapper.SerieDataMapper
import com.gabriel.data.features.serie.repository.GetSeriesRepositoryImpl
import com.gabriel.domain.features.filme.repository.GetFilmesRepository
import com.gabriel.domain.features.multiSearch.repository.MultiSearchRepository
import com.gabriel.domain.features.serie.repository.GetSeriesRepository
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

    // Multi modules
    factory<MultiSearchDataSource> { get() }
    factory { MultiDataMapper(get()) }
    factory<MultiSearchRepository> { MultiSearchRepositoryImpl(get()) }
}
