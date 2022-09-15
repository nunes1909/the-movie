package com.gabriel.data.movie.dataSource.serie

import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState

interface GetTrendingSerieDataSource {
    suspend fun getTrendingSerie(type: String): ResourceState<List<MovieData>>
}
