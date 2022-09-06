package com.gabriel.domain.features.serie.useCaseImpl

import com.gabriel.domain.features.serie.model.SerieDomain
import com.gabriel.domain.features.serie.repository.GetSeriesRepository
import com.gabriel.domain.features.serie.useCase.GetSeriesUseCase
import com.gabriel.domain.util.state.ResourceState

class GetSeriesUseCaseImpl(private val repository: GetSeriesRepository) : GetSeriesUseCase {
    override suspend fun getAllSeries(): ResourceState<List<SerieDomain>> {
        return repository.getAllSeries()
    }

    override suspend fun getDetailSerie(serieId: Int): ResourceState<SerieDomain> {
        return repository.getDetailSerie(serieId = serieId)
    }
}
