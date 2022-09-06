package com.gabriel.domain.features.serie.useCase

import com.gabriel.domain.features.serie.model.SerieDomain
import com.gabriel.domain.util.state.ResourceState

interface GetSeriesUseCase {
    suspend fun getAllSeries(): ResourceState<List<SerieDomain>>
    suspend fun getDetailSerie(serieId: Int): ResourceState<SerieDomain>
}
