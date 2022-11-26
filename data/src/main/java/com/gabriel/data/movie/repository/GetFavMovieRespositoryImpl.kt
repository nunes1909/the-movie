package com.gabriel.data.movie.repository

import com.gabriel.data.movie.dataStore.GetFavMovieDataStore
import com.gabriel.data.movie.mapper.MovieDataMapper
import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.movie.repository.GetFavMovieRespository
import com.gabriel.domain.util.state.ResourceState

class GetFavMovieRespositoryImpl(
    private val dataStore: GetFavMovieDataStore,
    private val mapper: MovieDataMapper
) : GetFavMovieRespository {
    override suspend fun getAllFav(query: String): ResourceState<List<MovieDomain>> {
        return validateState(dataStore.getAllFav(query = query))
    }

    private fun validateState(resourceState: ResourceState<List<MovieData>>):
            ResourceState<List<MovieDomain>> {
        if (resourceState.data != null) {
            val resultsDomain = mapper.mapToDomainNonNull(dataNonNull = resourceState.data!!)
            return ResourceState.Success(data = resultsDomain)
        }
        return ResourceState.Error(
            message = resourceState.message
        )
    }
}
