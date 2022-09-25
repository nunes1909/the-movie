package com.gabriel.cache.movie.favoritos.dataSourceImpl

import com.gabriel.cache.movie.favoritos.dao.FavoritosDao
import com.gabriel.cache.movie.favoritos.mapper.MovieCacheMapper
import com.gabriel.cache.movie.favoritos.model.MovieCache
import com.gabriel.data.movie.dataSource.movie.GetFavMovieCacheDataSource
import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class GetFavMovieCacheDataSourceImpl(
    private val dao: FavoritosDao,
    private val mapper: MovieCacheMapper
) : GetFavMovieCacheDataSource {
    override suspend fun getAllFav(query: String): Flow<ResourceState<List<MovieData>>> {
        return if (query.isEmpty()) {
            collectFlow(dao.getAllFav())
        } else {
            val queryLike = "%$query%"
            collectFlow(dao.getQueryFav(query = queryLike))
        }
    }

    private suspend fun collectFlow(flowAllFav: Flow<List<MovieCache>>) = flow {
        emit(
            validateState(flowAllFav.first())
        )
    }

    private fun validateState(resourceState: List<MovieCache>?):
            ResourceState<List<MovieData>> {
        if (resourceState != null) {
            val resultsDomain = mapper.mapToDataNonNull(resourceState)
            return ResourceState.Success(data = resultsDomain)
        }
        return ResourceState.Error(
            message = "Um erro interno ocorreu, não foi possível buscar os dados."
        )
    }
}
