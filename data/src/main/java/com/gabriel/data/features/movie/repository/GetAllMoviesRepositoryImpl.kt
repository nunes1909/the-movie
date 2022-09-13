package com.gabriel.data.features.movie.repository

import com.gabriel.data.features.movie.datasource.GetAllMoviesDataSource
import com.gabriel.data.features.movie.mapper.MovieDataMapper
import com.gabriel.domain.features.movie.model.MovieDomain
import com.gabriel.domain.features.movie.repository.GetAllMoviesRepository
import com.gabriel.domain.util.state.ResourceState

class GetAllMoviesRepositoryImpl(
    private val dataSource: GetAllMoviesDataSource,
    private val mapper: MovieDataMapper
) : GetAllMoviesRepository {
    override suspend fun getAllMovies(): ResourceState<List<MovieDomain>> {
        val resourceState = dataSource.getAllMovies()
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
