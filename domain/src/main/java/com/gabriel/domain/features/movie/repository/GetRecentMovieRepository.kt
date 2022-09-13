package com.gabriel.domain.features.movie.repository

import com.gabriel.domain.features.movie.model.MovieDomain
import com.gabriel.domain.util.state.ResourceState

interface GetRecentMovieRepository {
    suspend fun getRecentMovie(): ResourceState<MovieDomain>
}
