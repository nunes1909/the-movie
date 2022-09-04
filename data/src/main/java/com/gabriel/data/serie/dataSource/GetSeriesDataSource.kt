package com.gabriel.data.serie.dataSource

import com.gabriel.data.serie.model.SerieData

interface GetSeriesDataSource {
    suspend fun getAllSeries(): List<SerieData>
    suspend fun getFilterSerie(query: String): SerieData
}
