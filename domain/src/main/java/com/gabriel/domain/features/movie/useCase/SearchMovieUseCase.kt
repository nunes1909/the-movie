package com.gabriel.domain.features.movie.useCase

import com.gabriel.domain.features.movie.model.MovieDomain
import com.gabriel.domain.util.state.ResourceState

interface SearchMovieUseCase {
    suspend fun searchMovie(query: String): ResourceState<List<MovieDomain>>
}
