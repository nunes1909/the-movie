package com.gabriel.data.movie.dataStore

import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState
import kotlinx.coroutines.flow.Flow

interface GetFavMovieDataStore {
    suspend fun getAllFav(): Flow<ResourceState<List<MovieData>>>
}