package com.gabriel.cache.features.filme.mapper

import com.gabriel.cache.features.filme.model.FilmeCache
import com.gabriel.cache.features.genero.mapper.GeneroCacheMapper
import com.gabriel.cache.util.base.CacheMapper
import com.gabriel.data.features.filme.model.FilmeData

class FilmesCacheMapper(private val mapper: GeneroCacheMapper) :
    CacheMapper<FilmeCache, FilmeData> {
    override fun mapToData(type: FilmeCache): FilmeData {
        val generosCache = type.generos ?: listOf()
        val generosData = mapper.mapToData(generosCache)
        return FilmeData(
            type.id,
            title = type.title,
            description = type.description,
            nota = type.nota,
            background = type.background,
            banner = type.banner,
            generos = generosData,
            favorito = type.favorito
        )
    }

    override fun mapFromData(type: FilmeData): FilmeCache {
        val generosData = type.generos ?: listOf()
        val generosCache = mapper.mapFromData(generosData)
        return FilmeCache(
            id = type.id,
            title = type.title,
            description = type.description,
            nota = type.nota,
            background = type.background,
            banner = type.banner,
            generos = generosCache,
            favorito = type.favorito
        )
    }
}
