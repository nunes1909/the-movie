package com.gabriel.domain.features.filme.useCase

import com.gabriel.domain.features.filme.model.FilmeDomain
import com.gabriel.domain.util.state.ResourceState

interface GetTrandingFilmesUseCase {
    suspend fun getTrending(mediaType: String, timeWindow: String):
            ResourceState<List<FilmeDomain>>
}