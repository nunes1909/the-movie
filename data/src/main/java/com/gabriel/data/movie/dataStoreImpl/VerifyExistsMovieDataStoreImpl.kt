package com.gabriel.data.movie.dataStoreImpl

import com.gabriel.data.movie.dataSource.VerifyExistsMovieDataSource
import com.gabriel.data.movie.dataStore.VerifyExistsMovieDataStore
import com.gabriel.domain.util.state.ResourceState

class VerifyExistsMovieDataStoreImpl(private val source: VerifyExistsMovieDataSource):
    VerifyExistsMovieDataStore {
    override suspend fun verifyExistsMovie(id: Int): ResourceState<Boolean> {
        return source.verifyExistsMovie(id = id)
    }
}
