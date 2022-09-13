package com.gabriel.data.features.movie.dataSource.movie

import com.gabriel.data.features.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState

interface SearchMovieDataSource {
    suspend fun searchMovie(query: String): ResourceState<List<MovieData>>
}
