package com.gabriel.domain.features.movie.useCaseImpl

import com.gabriel.domain.features.movie.model.MovieDomain
import com.gabriel.domain.features.movie.repository.GetSimilarMoviesRepository
import com.gabriel.domain.features.movie.useCase.GetSimilarMoviesUseCase
import com.gabriel.domain.util.state.ResourceState

class GetSimilarMoviesUseCaseImpl(private val repository: GetSimilarMoviesRepository) :
    GetSimilarMoviesUseCase {
    override suspend fun getSimilarMovies(movieId: Int): ResourceState<List<MovieDomain>> {
        return getSimilarMovies(movieId = movieId)
    }
}
