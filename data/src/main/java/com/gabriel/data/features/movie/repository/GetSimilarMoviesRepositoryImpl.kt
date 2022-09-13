package com.gabriel.data.features.movie.repository

import com.gabriel.data.features.movie.mapper.MovieDataMapper
import com.gabriel.domain.features.movie.model.MovieDomain
import com.gabriel.domain.features.movie.repository.GetSimilarMoviesRepository
import com.gabriel.domain.util.state.ResourceState

class GetSimilarMoviesRepositoryImpl(
    private val dataSource: GetSimilarMoviesDataSource,
    private val mapper: MovieDataMapper
) : GetSimilarMoviesRepository {
    override suspend fun getSimilarMovies(movieId: Int, type: String): ResourceState<List<MovieDomain>> {
        val resourceState = dataSource.getSimilarMovies(movieId = movieId, type = type)
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
