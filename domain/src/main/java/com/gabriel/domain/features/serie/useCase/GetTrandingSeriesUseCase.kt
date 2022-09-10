package com.gabriel.domain.features.serie.useCase

import com.gabriel.domain.features.serie.model.SerieDomain
import com.gabriel.domain.util.state.ResourceState

interface GetTrandingSeriesUseCase {
    suspend fun getTrending(mediaType: String, timeWindow: String):
            ResourceState<List<SerieDomain>>
}
