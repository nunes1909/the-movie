package com.gabriel.data.movie.dataStoreImpl

import com.gabriel.data.movie.dataSource.movie.VerifyExistsMovieDataSource
import com.gabriel.data.movie.dataStore.VerifyExistsMovieDataStore
import kotlinx.coroutines.flow.Flow

class VerifyExistsMovieDataStoreImpl(private val source: VerifyExistsMovieDataSource):
    VerifyExistsMovieDataStore {
    override suspend fun verifyExistsMovie(id: Int): Flow<Boolean> {
        return source.verifyExistsMovie(id = id)
    }
}
