package com.gabriel.domain.movie.repository

import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.util.state.ResourceState

interface GetSimilarMoviesRepository {
    suspend fun getSimilarMovies(type: String, movieId: Int): ResourceState<List<MovieDomain>>
}
