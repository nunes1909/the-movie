package com.gabriel.data.movie.repository

import com.gabriel.data.movie.dataStore.DeleteMovieDataStore
import com.gabriel.data.movie.mapper.MovieDataMapper
import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.movie.repository.DeleteMovieRepository
import com.gabriel.domain.util.state.ResourceState

class DeleteMovieRepositoryImpl(
    private val dataStore: DeleteMovieDataStore,
    private val mapper: MovieDataMapper
): DeleteMovieRepository {
    override fun delete(entity: MovieDomain): ResourceState<Boolean> {
        val movieData = mapper.mapToData(type = entity)
        return dataStore.delete(entity = movieData)
    }
}
