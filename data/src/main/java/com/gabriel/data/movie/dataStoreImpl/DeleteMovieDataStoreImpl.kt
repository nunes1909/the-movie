package com.gabriel.data.movie.dataStoreImpl

import com.gabriel.data.movie.dataSource.movie.DeleteMovieDataSource
import com.gabriel.data.movie.dataStore.DeleteMovieDataStore
import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState

class DeleteMovieDataStoreImpl(private val source: DeleteMovieDataSource): DeleteMovieDataStore {
    override fun delete(entity: MovieData): ResourceState<Boolean> {
        return source.delete(entity = entity)
    }
}
