package com.gabriel.domain.movie.useCaseImpl

import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.movie.repository.GetTrendingMovieRepository
import com.gabriel.domain.movie.useCase.GetTrendingMovieUseCase
import com.gabriel.domain.util.state.ResourceState

class GetTrendingMovieUseCaseImpl(private val repository: GetTrendingMovieRepository) :
    GetTrendingMovieUseCase {
    override suspend fun getTrendingMovie(type: String): ResourceState<List<MovieDomain>> {
        return repository.getTrendingMovie(type = type)
    }
}
