package com.gabriel.domain.movie.useCase

import com.gabriel.domain.util.state.ResourceState

interface VerifyExistsMovieUseCase {
    suspend fun verifyExistsMovie(id: Int): ResourceState<Boolean>
}
