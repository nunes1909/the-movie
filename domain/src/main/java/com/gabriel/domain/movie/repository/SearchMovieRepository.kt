package com.gabriel.domain.movie.repository

import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.util.state.ResourceState

interface SearchMovieRepository {
    suspend fun searchMovie(query: String): ResourceState<List<MovieDomain>>
}
