package com.gabriel.data.movie.repository

import com.gabriel.data.movie.dataStore.GetAllMoviesDataStore
import com.gabriel.data.movie.mapper.MovieDataMapper
import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.movie.repository.GetAllMoviesRepository
import com.gabriel.domain.util.state.ResourceState

class GetAllMoviesRepositoryImpl(
    private val dataStore: GetAllMoviesDataStore,
    private val mapper: MovieDataMapper
) : GetAllMoviesRepository {
    override suspend fun getAllMovies(type: String): ResourceState<List<MovieDomain>> {
        val resourceState = dataStore.getAllMovies(type = type)
        if (resourceState.data != null) {
            val resultsDomain = mapper.mapToDomainNonNull(dataNonNull = resourceState.data!!)
            return ResourceState.Success(data = resultsDomain)
        }
        return ResourceState.Error(
            cod = resourceState.cod,
            message = resourceState.message
        )
    }
}
