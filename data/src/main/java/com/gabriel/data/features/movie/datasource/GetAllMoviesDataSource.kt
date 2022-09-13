package com.gabriel.data.features.movie.datasource

import com.gabriel.data.features.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState

interface GetAllMoviesDataSource {
    suspend fun getAllMovies(): ResourceState<List<MovieData>>
}
