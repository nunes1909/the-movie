package com.gabriel.remote.util.di

import com.gabriel.data.filme.dataSource.GetFilmesDataSource
import com.gabriel.remote.filme.dataSource.GetFilmesDataSourceImpl
import com.gabriel.remote.filme.mapper.FilmeDetailRemoteMapper
import com.gabriel.remote.filme.mapper.FilmeRemoteMapper
import com.gabriel.remote.filme.service.FilmesService
import com.gabriel.remote.genero.mapper.GeneroRemoteMapper
import com.gabriel.remote.genero.service.GeneroService
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
