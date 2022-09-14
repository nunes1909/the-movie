package com.gabriel.data.movie.repository

import com.gabriel.data.movie.dataStore.GetRecentMovieDataStore
import com.gabriel.data.movie.mapper.MovieDataMapper
import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.movie.repository.GetRecentMovieRepository
import com.gabriel.domain.util.state.ResourceState

class GetRecentMovieRepositoryImpl(
    private val dataStore: GetRecentMovieDataStore,
    private val mapper: MovieDataMapper
) : GetRecentMovieRepository {
    override suspend fun getRecentMovie(type: String): ResourceState<MovieDomain> {
        val resourceState = dataStore.getRecentMovie(type = type)
        if (resourceState.data != null) {
            val resultsDomain = mapper.mapToDomain(type = resourceState.data!!)
            return ResourceState.Undefined(data = resultsDomain)
        }
        return ResourceState.Undefined(
            cod = resourceState.cod,
            message = resourceState.message
        )
    }
}
