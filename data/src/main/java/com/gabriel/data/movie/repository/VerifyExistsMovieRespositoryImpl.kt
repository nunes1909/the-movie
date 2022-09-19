package com.gabriel.data.movie.repository

import com.gabriel.data.movie.dataStore.VerifyExistsMovieDataStore
import com.gabriel.domain.movie.repository.VerifyExistsMovieRespository
import com.gabriel.domain.util.state.ResourceState

class VerifyExistsMovieRespositoryImpl(
    private val dataStore: VerifyExistsMovieDataStore,
): VerifyExistsMovieRespository {
    override suspend fun verifyExistsMovie(idApi: Int): ResourceState<Boolean> {
        return dataStore.verifyExistsMovie(idApi = idApi)
    }
}
