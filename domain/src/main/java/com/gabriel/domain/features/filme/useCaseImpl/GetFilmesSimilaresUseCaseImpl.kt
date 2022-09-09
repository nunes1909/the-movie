package com.gabriel.domain.features.filme.useCaseImpl

import com.gabriel.domain.features.filme.model.FilmeDomain
import com.gabriel.domain.features.filme.repository.GetFilmesSimilaresRepository
import com.gabriel.domain.features.filme.useCase.GetFilmesSimilaresUseCase
import com.gabriel.domain.util.state.ResourceState

class GetFilmesSimilaresUseCaseImpl(
    private val repository: GetFilmesSimilaresRepository
): GetFilmesSimilaresUseCase {
    override suspend fun getFilmesSimilares(filmeId: Int): ResourceState<List<FilmeDomain>> {
        return repository.getFilmesSimilares(filmeId = filmeId)
    }
}
