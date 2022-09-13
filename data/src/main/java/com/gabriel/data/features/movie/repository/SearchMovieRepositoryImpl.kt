package com.gabriel.data.features.movie.repository

import com.gabriel.data.features.movie.dataStore.SearchMovieDataStore
import com.gabriel.data.features.movie.mapper.MovieDataMapper
import com.gabriel.domain.features.movie.model.MovieDomain
import com.gabriel.domain.features.movie.repository.SearchMovieRepository
import com.gabriel.domain.util.state.ResourceState

class SearchMovieRepositoryImpl(
    private val dataStore: SearchMovieDataStore,
    private val mapper: MovieDataMapper
) : SearchMovieRepository {
    override suspend fun searchMovie(query: String): ResourceState<List<MovieDomain>> {
        val resourceState = dataStore.searchMovie(query = query)
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
