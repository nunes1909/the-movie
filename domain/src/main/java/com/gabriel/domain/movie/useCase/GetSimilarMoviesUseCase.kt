package com.gabriel.domain.movie.useCase

import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.util.state.ResourceState

interface GetSimilarMoviesUseCase {
    suspend fun getSimilarMovies(movieId: Int, type: String): ResourceState<List<MovieDomain>>
}
