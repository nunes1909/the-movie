package com.gabriel.domain.features.serie.repository

import com.gabriel.domain.features.serie.model.SerieDomain

interface GetSeriesRepository {
    suspend fun getAllSeries(): List<SerieDomain>
    suspend fun getDetailSerie(serieId: Int): SerieDomain
}
