package com.gabriel.data.movie.dataStore

import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState

interface GetFavMovieDataStore {
    suspend fun getAllFav(query: String): ResourceState<List<MovieData>>
}