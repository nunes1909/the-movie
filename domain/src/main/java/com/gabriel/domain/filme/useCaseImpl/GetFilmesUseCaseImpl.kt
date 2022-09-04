package com.gabriel.domain.filme.useCaseImpl

import com.gabriel.domain.filme.model.FilmeDomain
import com.gabriel.domain.filme.repository.GetFilmesRepository
import com.gabriel.domain.filme.useCase.GetFilmesUseCase

class GetFilmesUseCaseImpl(private val repository: GetFilmesRepository) : GetFilmesUseCase {
    override suspend fun getAllFilmes(): List<FilmeDomain> {
        return repository.getAllFilmes()
    }

    override suspend fun getFilterFilme(query: String): FilmeDomain {
        return repository.getFilterFilme(query = query)
    }
}
