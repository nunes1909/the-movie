package com.gabriel.domain.movie.useCase

import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.util.state.ResourceState

interface GetTrendingMovieUseCase {
    suspend fun getTrendingMovie(type: String): ResourceState<List<MovieDomain>>
}
