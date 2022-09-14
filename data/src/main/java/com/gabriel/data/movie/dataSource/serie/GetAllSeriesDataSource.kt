package com.gabriel.data.movie.dataSource.serie

import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState

interface GetAllSeriesDataSource {
    suspend fun getAllSeries(type: String): ResourceState<List<MovieData>>
}
