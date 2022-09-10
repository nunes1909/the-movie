package com.gabriel.domain.features.serie.repository

import com.gabriel.domain.features.serie.model.SerieDomain
import com.gabriel.domain.util.state.ResourceState

interface GetTrandingSeriesRepository {
    suspend fun getTrending(mediaType: String, timeWindow: String):
            ResourceState<List<SerieDomain>>
}
