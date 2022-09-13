package com.gabriel.data.features.movie.dataSource

import com.gabriel.data.features.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState

interface GetRecentMovieDataSource {
    suspend fun getRecentMovie(type: String): ResourceState<MovieData>
}
