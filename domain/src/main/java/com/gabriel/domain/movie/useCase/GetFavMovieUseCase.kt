package com.gabriel.domain.movie.useCase

import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.util.state.ResourceState
import kotlinx.coroutines.flow.Flow

interface GetFavMovieUseCase {
    fun getAllFav(): Flow<ResourceState<List<MovieDomain>>>
}
