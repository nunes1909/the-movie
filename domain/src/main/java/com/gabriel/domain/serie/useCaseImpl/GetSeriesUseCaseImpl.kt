package com.gabriel.domain.serie.useCaseImpl

import com.gabriel.domain.serie.model.SerieDomain
import com.gabriel.domain.serie.repository.GetSeriesRepository
import com.gabriel.domain.serie.useCase.GetSeriesUseCase

class GetSeriesUseCaseImpl(private val repository: GetSeriesRepository) : GetSeriesUseCase {
    override suspend fun getAllSeries(): List<SerieDomain> {
        return repository.getAllSeries()
    }

    override suspend fun getDetailSerie(serieId: Int): SerieDomain {
        return repository.getDetailSerie(serieId = serieId)
    }
}
