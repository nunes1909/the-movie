package com.gabriel.domain.movie.repository

import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.util.state.ResourceState
import kotlinx.coroutines.flow.Flow

interface GetFavMovieRespository {
    suspend fun getAllFav(): Flow<ResourceState<List<MovieDomain>>>
}
