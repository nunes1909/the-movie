package com.gabriel.data.features.movie.repository

import com.gabriel.data.features.movie.dataSource.GetDetailMovieDataSource
import com.gabriel.data.features.movie.mapper.MovieDataMapper
import com.gabriel.domain.features.movie.model.MovieDomain
import com.gabriel.domain.features.movie.repository.GetDetailMovieRepository
import com.gabriel.domain.util.state.ResourceState

class GetDetailMovieRepositoryImpl(
    private val dataSource: GetDetailMovieDataSource,
    private val mapper: MovieDataMapper
) : GetDetailMovieRepository {
    override suspend fun getDetailMovie(movieId: Int, type: String): ResourceState<MovieDomain> {
        val resourceState = dataSource.getDetailMovie(movieId = movieId, type = type)
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
