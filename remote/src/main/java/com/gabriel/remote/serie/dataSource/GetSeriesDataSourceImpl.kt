package com.gabriel.remote.serie.dataSource

import com.gabriel.data.serie.dataSource.GetSeriesDataSource
import com.gabriel.data.serie.model.SerieData
import com.gabriel.remote.serie.mapper.SerieRemoteMapper
import com.gabriel.remote.serie.service.SeriesService

class GetSeriesDataSourceImpl(
    private val api: SeriesService,
    private val mapperSerie: SerieRemoteMapper,
    // Criar detail mapper
) : GetSeriesDataSource {
    override suspend fun getAllSeries(): List<SerieData> {

    }

    override suspend fun getDetailSerie(serieId: Int): SerieData {

    }
}