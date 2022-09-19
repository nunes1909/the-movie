package com.gabriel.domain.movie.useCaseImpl

import com.gabriel.domain.movie.repository.VerifyExistsMovieRespository
import com.gabriel.domain.movie.useCase.VerifyExistsMovieUseCase
import com.gabriel.domain.util.state.ResourceState

class VerifyExistsMovieUseCaseImpl(private val repository: VerifyExistsMovieRespository):
    VerifyExistsMovieUseCase {
    override suspend fun verifyExistsMovie(idApi: Int): ResourceState<Boolean> {
        return repository.verifyExistsMovie(idApi = idApi)
    }
}
