package com.gabriel.domain.filme.useCase

import com.gabriel.domain.filme.model.FilmeDomain
import com.gabriel.domain.util.state.ResourceState

interface GetFilmesUseCase {
    suspend fun getAllFilmes(): ResourceState<List<FilmeDomain>>
    suspend fun getDetailFilme(filmeId: Int): ResourceState<FilmeDomain>
}
