package com.gabriel.domain.filme.useCase

import com.gabriel.domain.filme.model.FilmeDomain

interface GetFilmesUseCase {
    suspend fun getAllFilmes(): List<FilmeDomain>
    suspend fun getFilterFilme(query: String): FilmeDomain
}
