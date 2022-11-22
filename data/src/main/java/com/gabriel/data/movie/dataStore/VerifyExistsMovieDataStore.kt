package com.gabriel.data.movie.dataStore

interface VerifyExistsMovieDataStore {
    suspend fun verifyExistsMovie(id: Int): Boolean
}
