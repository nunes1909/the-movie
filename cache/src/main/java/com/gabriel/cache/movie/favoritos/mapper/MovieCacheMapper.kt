package com.gabriel.cache.movie.favoritos.mapper

import com.gabriel.cache.movie.favoritos.model.FavoritosCache
import com.gabriel.cache.util.base.CacheMapper
import com.gabriel.data.movie.model.MovieData

class MovieCacheMapper : CacheMapper<FavoritosCache, MovieData> {
    override fun mapToData(type: FavoritosCache): MovieData {
        return MovieData(
            id = type.idApi,
            title = type.title,
            description = type.description,
            cartaz = type.cartaz,
            banner = type.banner
        )
    }

    override fun mapToCache(type: MovieData): FavoritosCache {
        return FavoritosCache(
            title = type.title,
            description = type.description,
            cartaz = type.cartaz,
            banner = type.banner,
            idApi = type.id
        )
    }
}
