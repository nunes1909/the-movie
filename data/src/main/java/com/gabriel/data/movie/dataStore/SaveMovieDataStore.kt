package com.gabriel.data.movie.dataStore

import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState
import kotlinx.coroutines.flow.Flow

interface SaveMovieDataStore {
    fun save(entity: MovieData): Flow<ResourceState<Boolean>>
}
