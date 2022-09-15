package com.gabriel.domain.movie.useCaseImpl

import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.movie.repository.GetSimilarMoviesRepository
import com.gabriel.domain.movie.useCase.GetSimilarMoviesUseCase
import com.gabriel.domain.util.state.ResourceState

class GetSimilarMoviesUseCaseImpl(private val repository: GetSimilarMoviesRepository) :
    GetSimilarMoviesUseCase {
    override suspend fun getSimilarMovies(
        type: String,
        movieId: Int
    ): ResourceState<List<MovieDomain>> {
        return repository.getSimilarMovies(movieId = movieId, type = type)
    }
}
