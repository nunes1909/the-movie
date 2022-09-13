package com.gabriel.data.features.movie.dataSource.filme

import com.gabriel.data.features.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState

interface GetDetailFilmeDataSource {
    suspend fun getDetailFilme(movieId: Int, type: String): ResourceState<MovieData>
}
