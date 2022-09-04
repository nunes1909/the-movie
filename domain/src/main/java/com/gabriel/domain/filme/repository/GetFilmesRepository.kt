package com.gabriel.domain.filme.repository

import com.gabriel.domain.filme.model.FilmeDomain

interface GetFilmesRepository {
    suspend fun getAllFilmes(): List<FilmeDomain>
    suspend fun getFilterFilme(query: String): FilmeDomain
}
