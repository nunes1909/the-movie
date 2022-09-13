package com.gabriel.domain.features.movie.useCase

import com.gabriel.domain.features.movie.model.MovieDomain
import com.gabriel.domain.util.state.ResourceState

interface GetAllMoviesUseCase {
    suspend fun getAllMovies(type: String): ResourceState<List<MovieDomain>>
}
