package com.gabriel.domain.movie.repository

import kotlinx.coroutines.flow.Flow

interface VerifyExistsMovieRespository {
    suspend fun verifyExistsMovie(id: Int): Flow<Boolean>
}
