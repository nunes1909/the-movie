package com.gabriel.domain.features.serie.useCase

import com.gabriel.domain.features.serie.model.SerieDomain
import com.gabriel.domain.util.state.ResourceState

interface GetSeriesSimilaresUseCase {
    suspend fun getSeriesSimilares(serieId: Int): ResourceState<List<SerieDomain>>
}
