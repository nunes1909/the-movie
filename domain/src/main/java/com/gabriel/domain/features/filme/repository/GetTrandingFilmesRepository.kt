package com.gabriel.domain.features.filme.repository

import com.gabriel.domain.features.filme.model.FilmeDomain
import com.gabriel.domain.util.state.ResourceState

interface GetTrandingFilmesRepository {
    suspend fun getTrending(mediaType: String, timeWindow: String = "day"):
            ResourceState<List<FilmeDomain>>
}
