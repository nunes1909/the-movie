package com.gabriel.data.movie.repository

import com.gabriel.data.movie.dataStore.DeleteMovieDataStore
import com.gabriel.data.movie.mapper.MovieDataMapper
import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.movie.repository.DeleteMovieRepository

class DeleteMovieRepositoryImpl(
    private val dataStore: DeleteMovieDataStore,
    private val mapper: MovieDataMapper
): DeleteMovieRepository {
    override fun delete(entity: MovieDomain) {
        val movieData = mapper.mapToData(type = entity)
        return dataStore.delete(entity = movieData)
    }
}
