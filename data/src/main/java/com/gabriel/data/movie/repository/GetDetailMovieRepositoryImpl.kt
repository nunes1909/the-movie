package com.gabriel.data.movie.repository

import com.gabriel.data.movie.dataStore.GetDetailMovieDataStore
import com.gabriel.data.movie.mapper.MovieDataMapper
import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.movie.repository.GetDetailMovieRepository
import com.gabriel.domain.util.state.ResourceState

class GetDetailMovieRepositoryImpl(
    private val dataStore: GetDetailMovieDataStore,
    private val mapper: MovieDataMapper
) : GetDetailMovieRepository {
    override suspend fun getDetailMovie(type: String, movieId: Int): ResourceState<MovieDomain> {
        val resourceState = dataStore.getDetailMovie(type = type, movieId = movieId)
        if (resourceState.data != null) {
            val resultsDomain = mapper.mapToDomain(type = resourceState.data!!)
            return ResourceState.Success(data = resultsDomain)
        }
        return ResourceState.Error(
            cod = resourceState.cod,
            message = resourceState.message
        )
    }
}
