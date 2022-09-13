package com.gabriel.data.features.movie.repository

import com.gabriel.data.features.movie.dataStore.GetAllMoviesDataStore
import com.gabriel.data.features.movie.mapper.MovieDataMapper
import com.gabriel.domain.features.movie.model.MovieDomain
import com.gabriel.domain.features.movie.repository.GetAllMoviesRepository
import com.gabriel.domain.util.state.ResourceState

class GetAllMoviesRepositoryImpl(
    private val dataStore: GetAllMoviesDataStore,
    private val mapper: MovieDataMapper
) : GetAllMoviesRepository {
    override suspend fun getAllMovies(type: String): ResourceState<List<MovieDomain>> {
        val resourceState = dataStore.getAllMovies(type = type)
        if (resourceState.data != null) {
            val resultsDomain = mapper.mapFromDomainNonNull(resourceState.data!!)
            return ResourceState.Undefined(data = resultsDomain)
        }
        return ResourceState.Undefined(
            cod = resourceState.cod,
            message = resourceState.message
        )
    }
}
