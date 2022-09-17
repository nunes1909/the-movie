package com.gabriel.data.movie.dataStoreImpl

import com.gabriel.data.movie.dataSource.movie.SaveMovieCacheDataSource
import com.gabriel.data.movie.dataStore.SaveMovieDataStore
import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState
import kotlinx.coroutines.flow.Flow

class SaveMovieDataStoreImpl(private val source: SaveMovieCacheDataSource) : SaveMovieDataStore {
    override fun save(entity: MovieData): Flow<ResourceState<Boolean>> {
        return source.save(entity = entity)
    }
}
