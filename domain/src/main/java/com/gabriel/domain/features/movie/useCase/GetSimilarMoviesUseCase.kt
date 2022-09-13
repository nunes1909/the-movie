package com.gabriel.domain.features.movie.useCase

import com.gabriel.domain.features.movie.model.MovieDomain
import com.gabriel.domain.util.state.ResourceState

interface GetSimilarMoviesUseCase {
    suspend fun getSimilarMovies(movieId: Int): ResourceState<List<MovieDomain>>
}
