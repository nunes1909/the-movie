package com.gabriel.data.features.serie.dataSource

import com.gabriel.data.features.serie.model.SerieData
import com.gabriel.domain.util.state.ResourceState

interface GetSeriesDataSource {
    suspend fun getAllSeries(): ResourceState<List<SerieData>>
    suspend fun getDetailSerie(serieId: Int): ResourceState<SerieData>
}
