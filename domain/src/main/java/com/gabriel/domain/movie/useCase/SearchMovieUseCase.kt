package com.gabriel.domain.movie.useCase

import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.util.state.ResourceState

interface SearchMovieUseCase {
    suspend fun searchMovie(query: String): ResourceState<List<MovieDomain>>
}
