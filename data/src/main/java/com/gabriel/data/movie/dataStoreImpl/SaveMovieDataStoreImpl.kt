package com.gabriel.data.movie.dataStoreImpl

import com.gabriel.data.movie.dataSource.movie.SaveMovieDataSource
import com.gabriel.data.movie.dataStore.SaveMovieDataStore
import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState

class SaveMovieDataStoreImpl(private val source: SaveMovieDataSource) : SaveMovieDataStore {
    override suspend fun save(entity: MovieData): ResourceState<Boolean> {
        return source.save(entity = entity)
    }
}
