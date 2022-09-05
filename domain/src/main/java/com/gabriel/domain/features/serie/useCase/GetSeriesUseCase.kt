package com.gabriel.domain.features.serie.useCase

import com.gabriel.domain.features.serie.model.SerieDomain

interface GetSeriesUseCase {
    suspend fun getAllSeries(): List<SerieDomain>
    suspend fun getDetailSerie(serieId: Int): SerieDomain
}
