package com.gabriel.domain.features.filme.useCaseImpl

import com.gabriel.domain.features.filme.model.FilmeDomain
import com.gabriel.domain.features.filme.repository.GetFilmesRepository
import com.gabriel.domain.features.filme.useCase.GetFilmesUseCase
import com.gabriel.domain.util.state.ResourceState

class GetFilmesUseCaseImpl(private val repository: GetFilmesRepository) : GetFilmesUseCase {
    override suspend fun getAllFilmes(): ResourceState<List<FilmeDomain>> {
        return repository.getAllFilmes()
    }

    override suspend fun getDetailFilme(filmeId: Int): ResourceState<FilmeDomain> {
        return repository.getDetailFilme(filmeId)
    }
}
