package com.gabriel.data.movie.repository

import com.gabriel.data.movie.dataStore.GetFavMovieDataStore
import com.gabriel.data.movie.mapper.MovieDataMapper
import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.movie.repository.GetFavMovieRespository
import com.gabriel.domain.util.state.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class GetFavMovieRespositoryImpl(
    private val dataStore: GetFavMovieDataStore,
    private val mapper: MovieDataMapper
) : GetFavMovieRespository {
    override suspend fun getAllFav(query: String): Flow<ResourceState<List<MovieDomain>>> {
        return collectFlow(dataStore.getAllFav(query = query))
    }

    private suspend fun collectFlow(flowAllFav: Flow<ResourceState<List<MovieData>>>) = flow {
        emit(
            validateState(flowAllFav.first())
        )
    }

    private fun validateState(resourceState: ResourceState<List<MovieData>>):
            ResourceState<List<MovieDomain>> {
        if (resourceState.data != null) {
            val resultsDomain = mapper.mapToDomainNonNull(entityNonNull = resourceState.data!!)
            return ResourceState.Success(data = resultsDomain)
        }
        return ResourceState.Error(
            message = resourceState.message
        )
    }
}
