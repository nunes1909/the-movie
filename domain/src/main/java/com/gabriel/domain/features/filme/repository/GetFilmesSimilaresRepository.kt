package com.gabriel.domain.features.filme.repository

import com.gabriel.domain.features.filme.model.FilmeDomain
import com.gabriel.domain.util.state.ResourceState

interface GetFilmesSimilaresRepository {
    suspend fun getFilmesSimilares(filmeId: Int): ResourceState<List<FilmeDomain>>
}
