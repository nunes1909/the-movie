package com.gabriel.data.movie.dataSource.filme

import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState

interface GetAllFilmesDataSource {
    suspend fun getAllMovies(type: String): ResourceState<List<MovieData>>
}
