package com.gabriel.domain.movie.useCase

import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.util.state.ResourceState

interface GetSimilarMoviesUseCase {
    suspend fun getSimilarMovies(type: String, movieId: Int): ResourceState<List<MovieDomain>>
}
