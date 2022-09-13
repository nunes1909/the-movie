package com.gabriel.domain.features.movie.useCase

import com.gabriel.domain.features.movie.model.MovieDomain
import com.gabriel.domain.util.state.ResourceState

interface GetDetailMovieUseCase {
    suspend fun getDetailMovie(type: String, movieId: Int): ResourceState<MovieDomain>
}
