package com.gabriel.domain.features.filme.useCase

import com.gabriel.domain.features.filme.model.FilmeDomain
import com.gabriel.domain.util.state.ResourceState

interface GetFilmesSimilaresUseCase {
    suspend fun getFilmesSimilares(filmeId: Int): ResourceState<List<FilmeDomain>>
}
