package com.gabriel.domain.movie.useCase

import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.util.state.ResourceState

interface GetAllMoviesUseCase {
    suspend fun getAllMovies(type: String): ResourceState<List<MovieDomain>>
}
