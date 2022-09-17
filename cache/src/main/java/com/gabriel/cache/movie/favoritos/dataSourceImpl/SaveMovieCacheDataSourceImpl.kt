package com.gabriel.cache.movie.favoritos.dataSourceImpl

import com.gabriel.cache.movie.favoritos.dao.FavoritosDao
import com.gabriel.cache.movie.favoritos.mapper.MovieCacheMapper
import com.gabriel.data.movie.dataSource.movie.SaveMovieCacheDataSource
import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState
import kotlinx.coroutines.flow.Flow

class SaveMovieCacheDataSourceImpl(
    private val dao: FavoritosDao,
    private val mapper: MovieCacheMapper
): SaveMovieCacheDataSource {
    override fun save(entity: MovieData): Flow<ResourceState<Boolean>> {
        val movieCache = mapper.mapToCache(type = entity)
        return dao.save(entity = movieCache)
    }
}
