package com.gabriel.domain.features.movie.useCaseImpl

import com.gabriel.domain.features.movie.model.MovieDomain
import com.gabriel.domain.features.movie.repository.GetAllMoviesRepository
import com.gabriel.domain.features.movie.useCase.GetAllMoviesUseCase
import com.gabriel.domain.util.state.ResourceState

class GetAllMoviesUseCaseImpl(private val repository: GetAllMoviesRepository) :
    GetAllMoviesUseCase {
    override suspend fun getAllMovies(): ResourceState<List<MovieDomain>> {
        return repository.getAllMovies()
    }
}
