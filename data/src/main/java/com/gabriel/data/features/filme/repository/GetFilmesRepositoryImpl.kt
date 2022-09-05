package com.gabriel.data.features.filme.repository

import com.gabriel.data.features.filme.dataSource.GetFilmesDataSource
import com.gabriel.data.features.filme.mapper.FilmeDataMapper
import com.gabriel.domain.features.filme.model.FilmeDomain
import com.gabriel.domain.features.filme.repository.GetFilmesRepository
import com.gabriel.domain.util.state.ResourceState

class GetFilmesRepositoryImpl(
    private val dataSource: GetFilmesDataSource,
    private val mapper: FilmeDataMapper
) : GetFilmesRepository {
    override suspend fun getAllFilmes(): ResourceState<List<FilmeDomain>> {
        val resourceState = dataSource.getAllFilmes()
        if (resourceState.data != null) {
            val resultsDomain = mapper.mapFromDomainNonNull(resourceState.data)
            return ResourceState.Undefined(data = resultsDomain)
        }
        return ResourceState.Undefined(
            cod = resourceState.cod,
            message = resourceState.message
        )
    }

    override suspend fun getDetailFilme(filmeId: Int): ResourceState<FilmeDomain> {
        val resourceState = dataSource.getDetailFilme(filmeId = filmeId)
        if (resourceState.data != null) {
            val resultDomain = mapper.mapToDomain(resourceState.data)
            return ResourceState.Undefined(resultDomain)
        }
        return ResourceState.Undefined(
            cod = resourceState.cod,
            message = resourceState.message
        )
    }
}
