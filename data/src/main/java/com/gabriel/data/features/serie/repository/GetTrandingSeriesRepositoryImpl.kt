package com.gabriel.data.features.serie.repository

import com.gabriel.data.features.serie.dataSource.GetTrandingSeriesDataSource
import com.gabriel.data.features.serie.mapper.SerieDataMapper
import com.gabriel.domain.features.serie.model.SerieDomain
import com.gabriel.domain.features.serie.repository.GetTrandingSeriesRepository
import com.gabriel.domain.util.state.ResourceState

class GetTrandingSeriesRepositoryImpl(
    private val dataSource: GetTrandingSeriesDataSource,
    private val mapper: SerieDataMapper
): GetTrandingSeriesRepository {
    override suspend fun getTrending(
        mediaType: String,
        timeWindow: String
    ): ResourceState<List<SerieDomain>> {
        val resourceState = dataSource.getTrending(mediaType = mediaType, timeWindow = timeWindow)
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