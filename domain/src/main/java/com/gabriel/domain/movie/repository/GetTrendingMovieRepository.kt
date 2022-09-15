package com.gabriel.domain.movie.repository

import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.util.state.ResourceState

interface GetTrendingMovieRepository {
    suspend fun getTrendingMovie(type: String): ResourceState<List<MovieDomain>>
}
