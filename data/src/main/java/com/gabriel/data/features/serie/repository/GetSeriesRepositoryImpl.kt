package com.gabriel.data.features.serie.repository

import com.gabriel.data.features.serie.dataSource.GetSeriesDataSource
import com.gabriel.data.features.serie.mapper.SerieDataMapper
import com.gabriel.domain.features.serie.model.SerieDomain
import com.gabriel.domain.features.serie.repository.GetSeriesRepository

class GetSeriesRepositoryImpl(
    private val dataSource: GetSeriesDataSource,
    private val mapper: SerieDataMapper
) : GetSeriesRepository {
    override suspend fun getAllSeries(): List<SerieDomain> {
        val listaData = dataSource.getAllSeries()
        return mapper.mapFromDomainNonNull(listaData)
    }

    override suspend fun getDetailSerie(serieId: Int): SerieDomain {
        val serieDomain = dataSource.getDetailSerie(serieId = serieId)
        return mapper.mapToDomain(serieDomain)
    }
}
