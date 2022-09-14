package com.gabriel.domain.movie.repository

import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.util.state.ResourceState

interface GetDetailMovieRepository {
    suspend fun getDetailMovie(type: String, movieId: Int): ResourceState<MovieDomain>
}
