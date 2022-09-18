package com.gabriel.domain.movie.useCaseImpl

import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.movie.repository.SaveMovieRepository
import com.gabriel.domain.movie.useCase.SaveMovieUseCase
import com.gabriel.domain.util.state.ResourceState

class SaveMovieUseCaseImpl(private val repository: SaveMovieRepository): SaveMovieUseCase {
    override suspend fun save(entity: MovieDomain): ResourceState<Boolean> {
        return repository.save(entity = entity)
    }
}
