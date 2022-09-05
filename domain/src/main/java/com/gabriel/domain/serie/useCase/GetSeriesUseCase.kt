package com.gabriel.domain.serie.useCase

import com.gabriel.domain.serie.model.SerieDomain

interface GetSeriesUseCase {
    suspend fun getAllSeries(): List<SerieDomain>
    suspend fun getDetailSerie(serieId: Int): SerieDomain
}