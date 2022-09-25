package com.gabriel.data.movie.dataStore

import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState

interface GetDetailMovieDataStore {
    suspend fun getDetailMovie(type: String, movieId: Int): ResourceState<MovieData>
}
