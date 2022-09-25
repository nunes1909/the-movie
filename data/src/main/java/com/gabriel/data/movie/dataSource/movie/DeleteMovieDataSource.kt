package com.gabriel.data.movie.dataSource.movie

import com.gabriel.data.movie.model.MovieData

interface DeleteMovieDataSource {
    fun delete(entity: MovieData)
}
