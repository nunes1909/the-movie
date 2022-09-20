package com.gabriel.cache.movie.favoritos.dataSourceImpl

import com.gabriel.cache.movie.favoritos.dao.FavoritosDao
import com.gabriel.cache.movie.favoritos.mapper.MovieCacheMapper
import com.gabriel.data.movie.dataSource.movie.DeleteMovieDataSource
import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState
import java.lang.Exception

class DeleteMovieDataSourceImpl(
    private val dao: FavoritosDao,
    private val mapper: MovieCacheMapper
): DeleteMovieDataSource {
    override fun delete(entity: MovieData): ResourceState<Boolean> {
        return try {
            val movieCache = mapper.mapToCache(type = entity)
            dao.delete(movieCache)
            ResourceState.Success(data = true)
        } catch (e: Exception) {
            ResourceState.Error(data = false)
        }
    }
}
