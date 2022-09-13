package com.gabriel.data.features.movie.repository

import com.gabriel.data.features.movie.datasource.GetRecentMovieDataSource
import com.gabriel.data.features.movie.mapper.MovieDataMapper
import com.gabriel.domain.features.movie.model.MovieDomain
import com.gabriel.domain.features.movie.repository.GetRecentMovieRepository
import com.gabriel.domain.util.state.ResourceState

class GetRecentMovieRepositoryImpl(
    private val dataSource: GetRecentMovieDataSource,
    private val mapper: MovieDataMapper
) : GetRecentMovieRepository {
    override suspend fun getRecentMovie(): ResourceState<MovieDomain> {
        val resourceState = dataSource.getRecentMovie()
        if (resourceState.data != null) {
            val resultsDomain = mapper.mapToDomain(resourceState.data!!)
            return ResourceState.Undefined(data = resultsDomain)
        }
        return ResourceState.Undefined(
            cod = resourceState.cod,
            message = resourceState.message
        )
    }
}
