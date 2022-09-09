package com.gabriel.data.features.filme.repository

import com.gabriel.data.features.filme.dataSource.GetTrandingFilmesDataSource
import com.gabriel.data.features.filme.mapper.FilmeDataMapper
import com.gabriel.domain.features.filme.model.FilmeDomain
import com.gabriel.domain.features.filme.repository.GetTrandingFilmesRepository
import com.gabriel.domain.util.state.ResourceState

class GetTrandingFilmesRepositoryImpl(
    private val dataSource: GetTrandingFilmesDataSource,
    private val mapper: FilmeDataMapper
) : GetTrandingFilmesRepository {
    override suspend fun getTrending(
        mediaType: String,
        timeWindow: String
    ): ResourceState<List<FilmeDomain>> {
        val resourceState = dataSource.getTrending(mediaType = mediaType, timeWindow = timeWindow)
        if (resourceState.data != null) {
            val resultsDomain = mapper.mapFromDomainNonNull(resourceState.data!!)
            return ResourceState.Undefined(data = resultsDomain)
        }
        return ResourceState.Undefined(
            cod = resourceState.cod,
            message = resourceState.message
        )
    }
}
