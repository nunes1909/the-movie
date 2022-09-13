package com.gabriel.data.features.movie.datasource

import com.gabriel.data.features.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState

interface GetRecentMovieDataSource {
    suspend fun getRecentMovie(): ResourceState<MovieData>
}
