package com.gabriel.domain.movie.useCaseImpl

import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.movie.repository.DeleteMovieRepository
import com.gabriel.domain.movie.useCase.DeleteMovieUseCase

class DeleteMovieUseCaseImpl(private val repository: DeleteMovieRepository): DeleteMovieUseCase {
    override fun delete(entity: MovieDomain) {
        return repository.delete(entity = entity)
    }
}
