package com.gabriel.domain.movie.repository

import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.util.state.ResourceState

interface GetAllMoviesRepository {
    suspend fun getAllMovies(type: String): ResourceState<List<MovieDomain>>
}
