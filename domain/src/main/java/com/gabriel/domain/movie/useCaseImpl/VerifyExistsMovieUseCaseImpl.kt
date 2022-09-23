package com.gabriel.domain.movie.useCaseImpl

import com.gabriel.domain.movie.repository.VerifyExistsMovieRespository
import com.gabriel.domain.movie.useCase.VerifyExistsMovieUseCase
import kotlinx.coroutines.flow.Flow

class VerifyExistsMovieUseCaseImpl(private val repository: VerifyExistsMovieRespository):
    VerifyExistsMovieUseCase {
    override suspend fun verifyExistsMovie(id: Int): Flow<Boolean> {
        return repository.verifyExistsMovie(id = id)
    }
}
