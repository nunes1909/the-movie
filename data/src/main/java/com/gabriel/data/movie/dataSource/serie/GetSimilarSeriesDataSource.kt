package com.gabriel.data.movie.dataSource.serie

import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState

interface GetSimilarSeriesDataSource {
    suspend fun getSimilarSeries(type: String, serieId: Int): ResourceState<List<MovieData>>
}
