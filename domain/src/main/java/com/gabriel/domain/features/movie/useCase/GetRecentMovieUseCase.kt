package com.gabriel.domain.features.movie.useCase

import com.gabriel.domain.features.movie.model.MovieDomain
import com.gabriel.domain.util.state.ResourceState

interface GetRecentMovieUseCase {
    suspend fun getRecentMovie(type: String): ResourceState<MovieDomain>
}
