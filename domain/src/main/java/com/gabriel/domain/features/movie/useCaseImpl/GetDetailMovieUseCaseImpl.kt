package com.gabriel.domain.features.movie.useCaseImpl

import com.gabriel.domain.features.movie.model.MovieDomain
import com.gabriel.domain.features.movie.repository.GetDetailMovieRepository
import com.gabriel.domain.features.movie.useCase.GetDetailMovieUseCase
import com.gabriel.domain.util.state.ResourceState

class GetDetailMovieUseCaseImpl(private val repository: GetDetailMovieRepository) :
    GetDetailMovieUseCase {
    override suspend fun getDetailMovie(movieId: Int, type: String): ResourceState<MovieDomain> {
        return repository.getDetailMovie(movieId = movieId, type = type)
    }
}
