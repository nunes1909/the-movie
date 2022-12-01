package com.gabriel.data.movie.dataSource.movie

import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState

interface GetFavMovieDataSource {
    suspend fun getAllFav(query: String): ResourceState<List<MovieData>>
}