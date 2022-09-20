package com.gabriel.domain.movie.useCaseImpl

import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.movie.repository.DeleteMovieRepository
import com.gabriel.domain.movie.useCase.DeleteMovieUseCase
import com.gabriel.domain.util.state.ResourceState

class DeleteMovieUseCaseImpl(private val repository: DeleteMovieRepository): DeleteMovieUseCase {
    override fun delete(entity: MovieDomain): ResourceState<Boolean> {
        return repository.delete(entity = entity)
    }
}
