package com.gabriel.domain.features.movie.useCaseImpl

import com.gabriel.domain.features.movie.model.MovieDomain
import com.gabriel.domain.features.movie.repository.GetRecentMovieRepository
import com.gabriel.domain.features.movie.useCase.GetRecentMovieUseCase
import com.gabriel.domain.util.state.ResourceState

class GetRecentMovieUseCaseImpl(private val repository: GetRecentMovieRepository): GetRecentMovieUseCase {
    override suspend fun getRecentMovie(type: String): ResourceState<MovieDomain> {
        return repository.getRecentMovie(type = type)
    }
}
