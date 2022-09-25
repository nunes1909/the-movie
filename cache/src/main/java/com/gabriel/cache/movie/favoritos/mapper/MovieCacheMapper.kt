package com.gabriel.cache.movie.favoritos.mapper

import com.gabriel.cache.movie.favoritos.model.MovieCache
import com.gabriel.cache.util.base.CacheMapper
import com.gabriel.data.movie.model.MovieData

class MovieCacheMapper : CacheMapper<MovieCache, MovieData> {
    override fun mapToData(type: MovieCache): MovieData {
        return MovieData(
            id = type.id,
            title = type.title,
            description = type.description,
            type = type.type,
            cartaz = type.cartaz,
            banner = type.banner
        )
    }

    override fun mapToCache(type: MovieData): MovieCache {
        return MovieCache(
            id = type.id,
            title = type.title,
            description = type.description,
            type = type.type,
            cartaz = type.cartaz,
            banner = type.banner
        )
    }
}
