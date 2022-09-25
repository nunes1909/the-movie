package com.gabriel.data.movie.repository

import com.gabriel.data.movie.dataStore.SearchMovieDataStore
import com.gabriel.data.movie.mapper.MovieDataMapper
import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.movie.repository.SearchMovieRepository
import com.gabriel.domain.util.state.ResourceState

class SearchMovieRepositoryImpl(
    private val dataStore: SearchMovieDataStore,
    private val mapper: MovieDataMapper
) : SearchMovieRepository {
    override suspend fun searchMovie(query: String): ResourceState<List<MovieDomain>> {
        val resourceState = dataStore.searchMovie(query = query)
        if (resourceState.data != null) {
            val resultsDomain = mapper.mapToDomainNonNull(resourceState.data!!)
            return ResourceState.Success(data = resultsDomain)
        }
        return ResourceState.Error(
            cod = resourceState.cod,
            message = resourceState.message
        )
    }
}
