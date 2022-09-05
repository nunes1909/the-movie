package com.gabriel.domain.serie.repository

import com.gabriel.domain.serie.model.SerieDomain

interface GetSeriesRepository {
    suspend fun getAllSeries(): List<SerieDomain>
    suspend fun getDetailSerie(serieId: Int): SerieDomain
}
