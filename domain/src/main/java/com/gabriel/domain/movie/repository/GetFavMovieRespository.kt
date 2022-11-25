package com.gabriel.domain.movie.repository

import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.util.state.ResourceState

interface GetFavMovieRespository {
    suspend fun getAllFav(query: String): ResourceState<List<MovieDomain>>
}
