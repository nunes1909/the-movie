package com.gabriel.domain.movie.useCaseImpl

import com.gabriel.domain.movie.repository.VerifyExistsMovieRespository
import com.gabriel.domain.movie.useCase.VerifyExistsMovieUseCase

class VerifyExistsMovieUseCaseImpl(private val repository: VerifyExistsMovieRespository):
    VerifyExistsMovieUseCase {
    override suspend fun verifyExistsMovie(id: Int): Boolean {
        return repository.verifyExistsMovie(id = id)
    }
}
