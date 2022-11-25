package com.gabriel.data.movie.repository

import com.gabriel.data.movie.dataStore.VerifyExistsMovieDataStore
import com.gabriel.domain.movie.repository.VerifyExistsMovieRespository

class VerifyExistsMovieRespositoryImpl(
    private val dataStore: VerifyExistsMovieDataStore,
): VerifyExistsMovieRespository {
    override suspend fun verifyExistsMovie(id: Int): Boolean {
        return dataStore.verifyExistsMovie(id = id)
    }
}
