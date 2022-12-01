package com.gabriel.domain.movie.useCase

interface VerifyExistsMovieUseCase {
    suspend fun verifyExistsMovie(id: Int): Boolean
}
