package com.gabriel.domain.movie.repository

interface VerifyExistsMovieRespository {
    suspend fun verifyExistsMovie(id: Int): Boolean
}
