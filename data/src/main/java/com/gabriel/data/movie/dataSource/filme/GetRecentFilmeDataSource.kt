package com.gabriel.data.movie.dataSource.filme

import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState

interface GetRecentFilmeDataSource {
    suspend fun getRecentFilme(type: String): ResourceState<MovieData>
}
