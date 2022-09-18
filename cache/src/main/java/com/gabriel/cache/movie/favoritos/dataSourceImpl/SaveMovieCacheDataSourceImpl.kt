package com.gabriel.cache.movie.favoritos.dataSourceImpl

import com.gabriel.cache.movie.favoritos.dao.FavoritosDao
import com.gabriel.cache.movie.favoritos.mapper.MovieCacheMapper
import com.gabriel.data.movie.dataSource.movie.SaveMovieCacheDataSource
import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState

class SaveMovieCacheDataSourceImpl(
    private val dao: FavoritosDao,
    private val mapper: MovieCacheMapper
): SaveMovieCacheDataSource {
    override suspend fun save(entity: MovieData): ResourceState<Boolean> {
        val movieCache = mapper.mapToCache(type = entity)
        return validateState(dao.save(entity = movieCache))
    }

    private fun validateState(id: Long?): ResourceState<Boolean> {
        if (id != null) {
            return ResourceState.Success(data = true)
        }
        return ResourceState.Error(message = "Não foi possível salvar.")
    }
}
