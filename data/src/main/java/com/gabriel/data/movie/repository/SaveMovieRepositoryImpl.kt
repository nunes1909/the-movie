package com.gabriel.data.movie.repository

import com.gabriel.data.movie.dataStore.SaveMovieDataStore
import com.gabriel.data.movie.mapper.MovieDataMapper
import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.movie.repository.SaveMovieRepository
import com.gabriel.domain.util.state.ResourceState
import kotlinx.coroutines.flow.Flow

class SaveMovieRepositoryImpl(
    private val dataStore: SaveMovieDataStore,
    private val mapper: MovieDataMapper
): SaveMovieRepository {
    override fun save(entity: MovieDomain): Flow<ResourceState<Boolean>> {
        val movieData = mapper.mapToData(type = entity)
        return dataStore.save(movieData)
    }
}
