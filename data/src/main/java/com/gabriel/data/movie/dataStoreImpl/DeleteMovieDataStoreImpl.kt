package com.gabriel.data.movie.dataStoreImpl

import com.gabriel.data.movie.dataSource.movie.DeleteMovieDataSource
import com.gabriel.data.movie.dataStore.DeleteMovieDataStore
import com.gabriel.data.movie.model.MovieData

class DeleteMovieDataStoreImpl(private val source: DeleteMovieDataSource): DeleteMovieDataStore {
    override fun delete(entity: MovieData) {
        return source.delete(entity = entity)
    }
}
