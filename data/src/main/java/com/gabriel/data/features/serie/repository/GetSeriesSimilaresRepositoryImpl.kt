package com.gabriel.data.features.serie.repository

import com.gabriel.data.features.serie.dataSource.GetSeriesSimilaresDataSource
import com.gabriel.data.features.serie.mapper.SerieDataMapper
import com.gabriel.domain.features.serie.model.SerieDomain
import com.gabriel.domain.features.serie.repository.GetSeriesSimilaresRepository
import com.gabriel.domain.util.state.ResourceState

class GetSeriesSimilaresRepositoryImpl(
    private val dataSource: GetSeriesSimilaresDataSource,
    private val mapper: SerieDataMapper
): GetSeriesSimilaresRepository {
    override suspend fun getSeriesSimilares(serieId: Int): ResourceState<List<SerieDomain>> {
        val resourceState = dataSource.getSeriesSimilares(serieId = serieId)
        if (resourceState.data != null) {
            val resultsDomain = mapper.mapFromDomainNonNull(resourceState.data!!)
            return ResourceState.Undefined(data = resultsDomain)
        }
        return ResourceState.Undefined(
            cod = resourceState.cod,
            message = resourceState.message
        )
    }
}
