package com.gabriel.data.movie.dataStore

import com.gabriel.domain.util.state.ResourceState

interface VerifyExistsMovieDataStore {
    suspend fun verifyExistsMovie(id: Int): ResourceState<Boolean>
}
