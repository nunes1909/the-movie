package com.gabriel.domain.movie.useCase

import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.util.state.ResourceState

interface SaveMovieUseCase {
    suspend fun save(entity: MovieDomain): ResourceState<Boolean>
}
