package com.gabriel.domain.features.serie.useCaseImpl

import com.gabriel.domain.features.serie.model.SerieDomain
import com.gabriel.domain.features.serie.repository.GetSeriesSimilaresRepository
import com.gabriel.domain.features.serie.useCase.GetSeriesSimilaresUseCase
import com.gabriel.domain.util.state.ResourceState

class GetSeriesSimilaresUseCaseImpl(
    private val repository: GetSeriesSimilaresRepository
) : GetSeriesSimilaresUseCase {
    override suspend fun getSeriesSimilares(serieId: Int): ResourceState<List<SerieDomain>> {
        return repository.getSeriesSimilares(serieId = serieId)
    }
}
