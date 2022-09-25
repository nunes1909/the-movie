package com.gabriel.data.movie.repository

import com.gabriel.data.movie.dataStore.GetTrendingMovieDataStore
import com.gabriel.data.movie.mapper.MovieDataMapper
import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.movie.repository.GetTrendingMovieRepository
import com.gabriel.domain.util.state.ResourceState

class GetTrendingMovieRepositoryImpl(
    private val dataStore: GetTrendingMovieDataStore,
    private val mapper: MovieDataMapper
) : GetTrendingMovieRepository {
    override suspend fun getTrendingMovie(type: String): ResourceState<List<MovieDomain>> {
        val resourceState = dataStore.getTrendingMovie(type = type)
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
