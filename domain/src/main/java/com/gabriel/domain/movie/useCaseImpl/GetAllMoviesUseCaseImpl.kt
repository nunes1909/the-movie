package com.gabriel.domain.movie.useCaseImpl

import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.movie.repository.GetAllMoviesRepository
import com.gabriel.domain.movie.useCase.GetAllMoviesUseCase
import com.gabriel.domain.util.state.ResourceState

class GetAllMoviesUseCaseImpl(private val repository: GetAllMoviesRepository) :
    GetAllMoviesUseCase {
    override suspend fun getAllMovies(type: String): ResourceState<List<MovieDomain>> {
        return repository.getAllMovies(type = type)
    }
}
