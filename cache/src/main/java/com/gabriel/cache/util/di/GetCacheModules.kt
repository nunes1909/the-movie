package com.gabriel.cache.util.di

import com.gabriel.cache.filme.mapper.FilmesCacheMapper
import com.gabriel.cache.genero.mapper.GeneroCacheMapper
import com.gabriel.cache.serie.mapper.SeriesCacheMapper
import org.koin.dsl.module

fun getCacheModules() = module {
    // Genero Modules
    factory { GeneroCacheMapper() }

    // Filme Modules
    factory { FilmesCacheMapper(get()) }

    // Serie Modules
    factory { SeriesCacheMapper(get()) }
}