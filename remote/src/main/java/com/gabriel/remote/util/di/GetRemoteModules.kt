package com.gabriel.remote.util.di

import com.gabriel.data.features.filme.dataSource.GetFilmesDataSource
import com.gabriel.remote.features.filme.dataSource.GetFilmesDataSourceImpl
import com.gabriel.remote.features.filme.mapper.FilmeDetailRemoteMapper
import com.gabriel.remote.features.filme.mapper.FilmeRemoteMapper
import com.gabriel.remote.features.filme.service.FilmesService
import com.gabriel.remote.features.genero.mapper.GeneroRemoteMapper
import com.gabriel.remote.features.genero.service.GeneroService
import org.koin.dsl.module

fun getRemoteModules() = module {
    // Genero Modules
    single<GeneroService> { get() }
    factory { GeneroRemoteMapper() }

    // Filmes Modules
    single<FilmesService> { get() }
    factory { FilmeRemoteMapper() }
    factory { FilmeDetailRemoteMapper(get()) }
    single<GetFilmesDataSource> { GetFilmesDataSourceImpl(get(), get(), get()) }

    // Series Modules

}
