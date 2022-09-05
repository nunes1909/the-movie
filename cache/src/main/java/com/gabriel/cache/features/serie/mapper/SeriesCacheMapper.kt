package com.gabriel.cache.features.serie.mapper

import com.gabriel.cache.features.genero.mapper.GeneroCacheMapper
import com.gabriel.cache.features.serie.model.SeriesCache
import com.gabriel.cache.util.base.CacheMapper
import com.gabriel.data.features.serie.model.SerieData

class SeriesCacheMapper(
    private val mapper: GeneroCacheMapper
) : CacheMapper<SeriesCache, SerieData> {
    override fun mapToData(type: SeriesCache): SerieData {
        val generosCache = type.generos ?: listOf()
        val generosData = mapper.mapToData(generosCache)
        return SerieData(
            id = type.id,
            title = type.title,
            description = type.description,
            nota = type.nota,
            background = type.background,
            banner = type.banner,
            generos = generosData,
            favorito = type.favorito
        )
    }

    override fun mapFromData(type: SerieData): SeriesCache {
        val generoData = type.generos ?: listOf()
        val generosCache = mapper.mapFromData(generoData)
        return SeriesCache(
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
