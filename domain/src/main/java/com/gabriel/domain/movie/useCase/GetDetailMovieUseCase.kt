package com.gabriel.domain.movie.useCase

import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.util.state.ResourceState

interface GetDetailMovieUseCase {
    suspend fun getDetailMovie(type: String, movieId: Int): ResourceState<MovieDomain>
}
