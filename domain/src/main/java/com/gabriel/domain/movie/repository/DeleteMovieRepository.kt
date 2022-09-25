package com.gabriel.domain.movie.repository

import com.gabriel.domain.movie.model.MovieDomain

interface DeleteMovieRepository {
    fun delete(entity: MovieDomain)
}
