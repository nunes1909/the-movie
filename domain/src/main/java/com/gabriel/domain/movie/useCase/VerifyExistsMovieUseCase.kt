package com.gabriel.domain.movie.useCase

import kotlinx.coroutines.flow.Flow

interface VerifyExistsMovieUseCase {
    suspend fun verifyExistsMovie(id: Int): Flow<Boolean>
}
