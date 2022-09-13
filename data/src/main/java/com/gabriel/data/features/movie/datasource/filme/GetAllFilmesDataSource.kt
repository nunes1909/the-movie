package com.gabriel.data.features.movie.dataSource.filme

import com.gabriel.data.features.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState

interface GetAllFilmesDataSource {
    suspend fun getAllMovies(type: String): ResourceState<List<MovieData>>
}
