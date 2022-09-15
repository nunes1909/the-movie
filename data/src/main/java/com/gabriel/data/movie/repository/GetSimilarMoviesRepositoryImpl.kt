package com.gabriel.data.movie.repository

import com.gabriel.data.movie.dataStore.GetSimilarMoviesDataStore
import com.gabriel.data.movie.mapper.MovieDataMapper
import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.movie.repository.GetSimilarMoviesRepository
import com.gabriel.domain.util.state.ResourceState

class GetSimilarMoviesRepositoryImpl(
    private val dataStore: GetSimilarMoviesDataStore,
    private val mapper: MovieDataMapper
) : GetSimilarMoviesRepository {
    override suspend fun getSimilarMovies(
        type: String,
        movieId: Int
    ): ResourceState<List<MovieDomain>> {
        val resourceState = dataStore.getSimilarMovies(movieId = movieId, type = type)
        if (resourceState.data != null) {
            val resultsDomain = mapper.mapToDomainNonNull(resourceState.data!!)
            return ResourceState.Undefined(data = resultsDomain)
        }
        return ResourceState.Undefined(
            cod = resourceState.cod,
            message = resourceState.message
        )
    }
}
