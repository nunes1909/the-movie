package com.gabriel.data.movie.dataStore

import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState

interface GetTrendingMovieDataStore {
    suspend fun getTrendingMovie(type: String): ResourceState<List<MovieData>>
}
