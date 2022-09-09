package com.gabriel.data.features.filme.repository

import com.gabriel.data.features.filme.dataSource.GetFilmesSimilaresDataSource
import com.gabriel.data.features.filme.mapper.FilmeDataMapper
import com.gabriel.domain.features.filme.model.FilmeDomain
import com.gabriel.domain.features.filme.repository.GetFilmesSimilaresRepository
import com.gabriel.domain.util.state.ResourceState

class GetFilmesSimilaresRepositoryImpl(
    private val dataSource: GetFilmesSimilaresDataSource,
    private val mapper: FilmeDataMapper
): GetFilmesSimilaresRepository {
    override suspend fun getFilmesSimilares(filmeId: Int): ResourceState<List<FilmeDomain>> {
        val resourceState = dataSource.getFilmesSimilares(filmeId = filmeId)
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
