package com.gabriel.data.features.movie.dataSource

import com.gabriel.data.features.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState

interface GetAllSeriesDataSource {
    suspend fun getAllSeries(type: String): ResourceState<List<MovieData>>
}
