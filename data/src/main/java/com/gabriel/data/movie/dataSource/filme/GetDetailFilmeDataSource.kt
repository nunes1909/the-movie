package com.gabriel.data.movie.dataSource.filme

import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState

interface GetDetailFilmeDataSource {
    suspend fun getDetailFilme(type: String, movieId: Int): ResourceState<MovieData>
}
