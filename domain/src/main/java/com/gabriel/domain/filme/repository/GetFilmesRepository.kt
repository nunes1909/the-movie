package com.gabriel.domain.filme.repository

import com.gabriel.domain.filme.model.FilmeDomain
import com.gabriel.domain.util.state.ResourceState

interface GetFilmesRepository {
    suspend fun getAllFilmes(): ResourceState<List<FilmeDomain>>
    suspend fun getSearchFilmes(): ResourceState<List<FilmeDomain>>
    suspend fun getDetailFilme(filmeId: Int): ResourceState<FilmeDomain>
}
