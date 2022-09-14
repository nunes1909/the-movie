package com.gabriel.data.movie.dataSource.serie

import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState

interface GetDetailSerieDataSource {
    suspend fun getDetailSerie(type: String, movieId: Int): ResourceState<MovieData>
}
