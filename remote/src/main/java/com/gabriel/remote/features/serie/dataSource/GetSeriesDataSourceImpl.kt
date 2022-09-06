package com.gabriel.remote.features.serie.dataSource

import com.gabriel.data.features.serie.dataSource.GetSeriesDataSource
import com.gabriel.data.features.serie.model.SerieData
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.remote.features.serie.mapper.SerieRemoteMapper
import com.gabriel.remote.features.serie.service.SeriesService

class GetSeriesDataSourceImpl(
    private val api: SeriesService,
    private val mapperSerie: SerieRemoteMapper,
    // Criar detail mapper
) : GetSeriesDataSource {
    override suspend fun getAllSeries(): ResourceState<List<SerieData>> {

    }

    override suspend fun getDetailSerie(serieId: Int): ResourceState<SerieData> {

    }
}