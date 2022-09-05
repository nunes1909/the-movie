package com.gabriel.data.features.serie.dataSource

import com.gabriel.data.features.serie.model.SerieData

interface GetSeriesDataSource {
    suspend fun getAllSeries(): List<SerieData>
    suspend fun getDetailSerie(serieId: Int): SerieData
}
