package com.gabriel.data.movie.dataStore

import com.gabriel.data.movie.model.MovieData

interface DeleteMovieDataStore {
    fun delete(entity: MovieData)
}
