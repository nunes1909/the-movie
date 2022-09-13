package com.gabriel.data.features.movie.dataStore

import com.gabriel.data.features.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState

interface SearchMovieDataStore {
    suspend fun searchMovie(query: String): ResourceState<List<MovieData>>
}
