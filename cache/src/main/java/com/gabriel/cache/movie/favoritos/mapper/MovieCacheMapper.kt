package com.gabriel.cache.movie.favoritos.mapper

import com.gabriel.cache.movie.favoritos.model.FavoritoCache
import com.gabriel.cache.util.base.CacheMapper
import com.gabriel.data.movie.model.MovieData

class MovieCacheMapper : CacheMapper<FavoritoCache, MovieData> {
    override fun mapToData(type: FavoritoCache): MovieData {
        return MovieData(
            id = type.idApi,
            title = type.title,
            description = type.description,
            cartaz = type.cartaz,
            banner = type.banner
        )
    }

    override fun mapToCache(type: MovieData): FavoritoCache {
        return FavoritoCache(
            title = type.title,
            description = type.description,
            cartaz = type.cartaz,
            banner = type.banner,
            idApi = type.id
        )
    }
}
