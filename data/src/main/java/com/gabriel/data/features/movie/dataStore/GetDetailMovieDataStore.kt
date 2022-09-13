package com.gabriel.data.features.movie.dataStore

import com.gabriel.data.features.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState

interface GetDetailMovieDataStore {
    suspend fun getDetailMovie(movieId: Int, type: String): ResourceState<MovieData>
}
