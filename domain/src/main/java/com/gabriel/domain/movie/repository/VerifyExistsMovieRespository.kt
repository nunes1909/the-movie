package com.gabriel.domain.movie.repository

import com.gabriel.domain.util.state.ResourceState

interface VerifyExistsMovieRespository {
    suspend fun verifyExistsMovie(id: Int): ResourceState<Boolean>
}
