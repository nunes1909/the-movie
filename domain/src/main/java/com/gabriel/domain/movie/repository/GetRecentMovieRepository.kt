package com.gabriel.domain.movie.repository

import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.util.state.ResourceState

interface GetRecentMovieRepository {
    suspend fun getRecentMovie(type: String): ResourceState<MovieDomain>
}
