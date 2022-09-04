package com.gabriel.cache.genero.mapper

import com.gabriel.cache.genero.model.GeneroCache
import com.gabriel.cache.util.base.CacheMapper
import com.gabriel.data.genero.model.GeneroData

class GeneroCacheMapper : CacheMapper<GeneroCache, GeneroData> {
    override fun mapToData(type: GeneroCache): GeneroData {
        return GeneroData(
            id = type.id,
            name = type.name
        )
    }

    override fun mapFromData(type: GeneroData): GeneroCache {
        return GeneroCache(
            id = type.id,
            name = type.name
        )
    }
}
