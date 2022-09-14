package com.gabriel.domain.movie.repository

import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.util.state.ResourceState

interface GetSimilarMoviesRepository {
    suspend fun getSimilarMovies(movieId: Int, type: String): ResourceState<List<MovieDomain>>
}
