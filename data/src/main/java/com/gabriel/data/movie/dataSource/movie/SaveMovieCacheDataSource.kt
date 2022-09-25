package com.gabriel.data.movie.dataSource.movie

import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState
import kotlinx.coroutines.flow.Flow

interface SaveMovieCacheDataSource {
    suspend fun save(entity: MovieData): ResourceState<Boolean>
}
