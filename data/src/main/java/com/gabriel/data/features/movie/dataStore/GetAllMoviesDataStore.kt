package com.gabriel.data.features.movie.dataStore

import com.gabriel.data.features.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState

interface GetAllMoviesDataStore {
    suspend fun getAllMovies(type: String): ResourceState<List<MovieData>>
}
