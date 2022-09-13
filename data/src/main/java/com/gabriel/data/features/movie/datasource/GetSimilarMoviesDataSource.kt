package com.gabriel.data.features.movie.datasource

import com.gabriel.data.features.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState

interface GetSimilarMoviesDataSource {
    suspend fun getSimilarMovies(movieId: Int): ResourceState<List<MovieData>>
}
