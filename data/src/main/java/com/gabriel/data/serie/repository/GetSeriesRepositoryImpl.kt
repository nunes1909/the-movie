package com.gabriel.data.serie.repository

import com.gabriel.data.serie.dataSource.GetSeriesDataSource
import com.gabriel.data.serie.mapper.SerieDataMapper
import com.gabriel.domain.serie.model.SerieDomain
import com.gabriel.domain.serie.repository.GetSeriesRepository

class GetSeriesRepositoryImpl(
    private val dataSource: GetSeriesDataSource,
    private val mapper: SerieDataMapper
) : GetSeriesRepository {
    override suspend fun getAllSeries(): List<SerieDomain> {
        val listaData = dataSource.getAllSeries()
        return mapper.mapFromDomainNonNull(listaData)
    }

    override suspend fun getFilterSerie(query: String): SerieDomain {
        val serieDomain = dataSource.getFilterSerie(query = query)
        return mapper.mapToDomain(serieDomain)
    }
}
