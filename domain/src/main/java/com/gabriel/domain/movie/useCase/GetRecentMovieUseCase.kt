package com.gabriel.domain.movie.useCase

import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.util.state.ResourceState

interface GetRecentMovieUseCase {
    suspend fun getRecentMovie(type: String): ResourceState<MovieDomain>
}
