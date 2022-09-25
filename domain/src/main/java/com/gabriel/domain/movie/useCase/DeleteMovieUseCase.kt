package com.gabriel.domain.movie.useCase

import com.gabriel.domain.movie.model.MovieDomain

interface DeleteMovieUseCase {
    fun delete(entity: MovieDomain)
}
