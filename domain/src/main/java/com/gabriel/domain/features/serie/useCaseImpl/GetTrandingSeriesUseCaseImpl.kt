package com.gabriel.domain.features.serie.useCaseImpl

import com.gabriel.domain.features.serie.model.SerieDomain
import com.gabriel.domain.features.serie.repository.GetTrandingSeriesRepository
import com.gabriel.domain.features.serie.useCase.GetTrandingSeriesUseCase
import com.gabriel.domain.util.state.ResourceState

class GetTrandingSeriesUseCaseImpl(private val repository: GetTrandingSeriesRepository):
    GetTrandingSeriesUseCase {
    override suspend fun getTrending(
        mediaType: String,
        timeWindow: String
    ): ResourceState<List<SerieDomain>> {
        return repository.getTrending(mediaType = mediaType, timeWindow = timeWindow)
    }
}
