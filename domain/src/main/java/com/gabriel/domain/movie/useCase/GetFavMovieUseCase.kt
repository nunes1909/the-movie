package com.gabriel.domain.movie.useCase

import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.util.state.ResourceState

interface GetFavMovieUseCase {
    suspend fun getAllFav(query: String): ResourceState<List<MovieDomain>>
}
