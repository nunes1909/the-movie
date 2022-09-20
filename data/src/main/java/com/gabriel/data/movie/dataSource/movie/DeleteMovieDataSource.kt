package com.gabriel.data.movie.dataSource.movie

import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState

interface DeleteMovieDataSource {
    fun delete(entity: MovieData): ResourceState<Boolean>
}
