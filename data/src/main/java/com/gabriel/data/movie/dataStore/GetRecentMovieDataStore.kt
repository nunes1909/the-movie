package com.gabriel.data.movie.dataStore

import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState

interface GetRecentMovieDataStore {
    suspend fun getRecentMovie(type: String): ResourceState<MovieData>
}
