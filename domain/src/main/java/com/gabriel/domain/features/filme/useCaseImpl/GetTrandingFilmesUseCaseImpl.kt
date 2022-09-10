package com.gabriel.domain.features.filme.useCaseImpl

import com.gabriel.domain.features.filme.model.FilmeDomain
import com.gabriel.domain.features.filme.repository.GetTrandingFilmesRepository
import com.gabriel.domain.features.filme.useCase.GetTrandingFilmesUseCase
import com.gabriel.domain.util.state.ResourceState

class GetTrandingFilmesUseCaseImpl(private val repository: GetTrandingFilmesRepository) :
    GetTrandingFilmesUseCase {
    override suspend fun getTrending(
        mediaType: String,
        timeWindow: String
    ): ResourceState<List<FilmeDomain>> {
        return repository.getTrending(mediaType = mediaType, timeWindow = timeWindow)
    }
}