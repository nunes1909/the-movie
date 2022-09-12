package com.gabriel.data.features.serie.dataSource

import com.gabriel.data.features.serie.model.SerieData
import com.gabriel.domain.util.state.ResourceState

interface GetSeriesSimilaresDataSource {
    suspend fun getSeriesSimilares(serieId: Int): ResourceState<List<SerieData>>
}
