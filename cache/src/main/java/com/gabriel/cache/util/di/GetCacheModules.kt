package com.gabriel.cache.util.di

import com.gabriel.cache.features.filme.mapper.FilmesCacheMapper
import com.gabriel.cache.features.genero.mapper.GeneroCacheMapper
import com.gabriel.cache.features.serie.mapper.SeriesCacheMapper
import org.koin.dsl.module

fun getCacheModules() = module {
    // Genero Modules
    factory { GeneroCacheMapper() }

    // Filme Modules
    factory { FilmesCacheMapper(get()) }

    // Serie Modules
    factory { SeriesCacheMapper(get()) }
}