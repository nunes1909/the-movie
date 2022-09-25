package com.gabriel.data.movie.dataStore

import kotlinx.coroutines.flow.Flow

interface VerifyExistsMovieDataStore {
    suspend fun verifyExistsMovie(id: Int): Flow<Boolean>
}
