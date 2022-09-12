package com.gabriel.domain.features.serie.repository

import com.gabriel.domain.features.serie.model.SerieDomain
import com.gabriel.domain.util.state.ResourceState

interface GetSeriesSimilaresRepository {
    suspend fun getSeriesSimilares(serieId: Int): ResourceState<List<SerieDomain>>
}
