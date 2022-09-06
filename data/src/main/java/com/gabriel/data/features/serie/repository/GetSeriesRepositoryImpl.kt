package com.gabriel.data.features.serie.repository

import com.gabriel.data.features.serie.dataSource.GetSeriesDataSource
import com.gabriel.data.features.serie.mapper.SerieDataMapper
import com.gabriel.domain.features.serie.model.SerieDomain
import com.gabriel.domain.features.serie.repository.GetSeriesRepository
import com.gabriel.domain.util.state.ResourceState

class GetSeriesRepositoryImpl(
    private val dataSource: GetSeriesDataSource,
    private val mapper: SerieDataMapper
) : GetSeriesRepository {
    override suspend fun getAllSeries(): ResourceState<List<SerieDomain>> {
        val resource = dataSource.getAllSeries()
        if (resource.data != null) {
            val resultsDomain = mapper.mapFromDomainNonNull(resource.data!!)
            return ResourceState.Undefined(data = resultsDomain)
        }
        return ResourceState.Undefined(
            cod = resource.cod,
            message = resource.message
        )
    }

    override suspend fun getDetailSerie(serieId: Int): ResourceState<SerieDomain> {
        val resource = dataSource.getDetailSerie(serieId = serieId)
        if (resource.data != null) {
            val resultDomain = mapper.mapToDomain(resource.data!!)
            return ResourceState.Undefined(data = resultDomain)
        }
        return ResourceState.Undefined(
            cod = resource.cod,
            message = resource.message
        )
    }
}
