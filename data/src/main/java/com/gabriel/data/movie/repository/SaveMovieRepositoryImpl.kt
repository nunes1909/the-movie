package com.gabriel.data.movie.repository

import com.gabriel.data.movie.dataStore.SaveMovieDataStore
import com.gabriel.data.movie.mapper.MovieDataMapper
import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.movie.repository.SaveMovieRepository
import com.gabriel.domain.util.state.ResourceState

class SaveMovieRepositoryImpl(
    private val dataStore: SaveMovieDataStore,
    private val mapper: MovieDataMapper
): SaveMovieRepository {
    override suspend fun save(entity: MovieDomain): ResourceState<Boolean> {
        val movieData = mapper.mapToData(type = entity)
        return dataStore.save(movieData)
    }
}
