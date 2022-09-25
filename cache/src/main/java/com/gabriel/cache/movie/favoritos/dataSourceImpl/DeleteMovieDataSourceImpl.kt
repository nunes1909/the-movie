package com.gabriel.cache.movie.favoritos.dataSourceImpl

import com.gabriel.cache.movie.favoritos.dao.FavoritosDao
import com.gabriel.cache.movie.favoritos.mapper.MovieCacheMapper
import com.gabriel.data.movie.dataSource.movie.DeleteMovieDataSource
import com.gabriel.data.movie.model.MovieData

class DeleteMovieDataSourceImpl(
    private val dao: FavoritosDao,
    private val mapper: MovieCacheMapper
): DeleteMovieDataSource {
    override fun delete(entity: MovieData) {
        val movieCache = mapper.mapToCache(type = entity)
        return dao.delete(movieCache)
    }
}
