package com.gabriel.domain.filme.useCaseImpl

import com.gabriel.domain.filme.model.FilmeDomain
import com.gabriel.domain.filme.repository.GetFilmesRepository
import com.gabriel.domain.filme.useCase.GetFilmesUseCase
import com.gabriel.domain.util.state.ResourceState

class GetFilmesUseCaseImpl(private val repository: GetFilmesRepository) : GetFilmesUseCase {
    override suspend fun getAllFilmes(): ResourceState<List<FilmeDomain>> {
        return repository.getAllFilmes()
    }

    override suspend fun getSearchFilmes(): ResourceState<List<FilmeDomain>> {
        return repository.getSearchFilmes()
    }

    override suspend fun getDetailFilme(filmeId: Int): ResourceState<FilmeDomain> {
        return repository.getDetailFilme(filmeId)
    }
}
