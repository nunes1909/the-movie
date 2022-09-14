package com.gabriel.domain.movie.useCaseImpl

import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.movie.repository.GetDetailMovieRepository
import com.gabriel.domain.movie.useCase.GetDetailMovieUseCase
import com.gabriel.domain.util.state.ResourceState

class GetDetailMovieUseCaseImpl(private val repository: GetDetailMovieRepository) :
    GetDetailMovieUseCase {
    override suspend fun getDetailMovie(type: String, movieId: Int): ResourceState<MovieDomain> {
        return repository.getDetailMovie(type = type, movieId = movieId)
    }
}
