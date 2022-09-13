package com.gabriel.domain.features.movie.repository

import com.gabriel.domain.features.movie.model.MovieDomain
import com.gabriel.domain.util.state.ResourceState

interface GetAllMoviesRepository {
    suspend fun getAllMovies(type: String): ResourceState<List<MovieDomain>>
}
