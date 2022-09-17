package com.gabriel.cache.movie.favoritos.dataSourceImpl

import com.gabriel.cache.movie.favoritos.dao.FavoritosDao
import com.gabriel.cache.movie.favoritos.mapper.MovieCacheMapper
import com.gabriel.cache.movie.favoritos.model.FavoritoCache
import com.gabriel.data.movie.dataSource.movie.GetFavMovieCacheDataSource
import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetFavMovieCacheDataSourceImpl(
    private val dao: FavoritosDao,
    private val mapper: MovieCacheMapper
) : GetFavMovieCacheDataSource {
    override suspend fun getAllFav(): Flow<ResourceState<List<MovieData>>> {
        return collectFlow(dao.getAllFav())
    }

    private suspend fun collectFlow(flowAllFav: Flow<ResourceState<List<FavoritoCache>>>) = flow {
        flowAllFav.collect { resourceState ->
            emit(validateState(resourceState = resourceState))
        }
    }

    private fun validateState(resourceState: ResourceState<List<FavoritoCache>>):
            ResourceState<List<MovieData>> {
        if (resourceState.data != null) {
            val resultsDomain = mapper.mapToDataNonNull(resourceState.data!!)
            return ResourceState.Undefined(data = resultsDomain)
        }
        return ResourceState.Undefined(
            message = "Um erro interno ocorreu, não foi possível buscar os dados."
        )
    }
}
