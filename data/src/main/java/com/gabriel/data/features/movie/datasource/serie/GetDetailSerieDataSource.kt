package com.gabriel.data.features.movie.dataSource.serie

import com.gabriel.data.features.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState

interface GetDetailSerieDataSource {
    suspend fun getDetailSerie(movieId: Int, type: String): ResourceState<MovieData>
}
