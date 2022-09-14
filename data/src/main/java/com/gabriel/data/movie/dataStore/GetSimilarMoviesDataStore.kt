package com.gabriel.data.movie.dataStore

import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState

interface GetSimilarMoviesDataStore {
    suspend fun getSimilarMovies(type: String, movieId: Int): ResourceState<List<MovieData>>
}
