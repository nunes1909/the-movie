package com.gabriel.data.features.multiSearch.repository

import com.gabriel.data.features.multiSearch.dataSource.MultiSearchDataSource
import com.gabriel.data.features.multiSearch.mapper.MultiDataMapper
import com.gabriel.domain.features.multiSearch.model.MultiDomain
import com.gabriel.domain.features.multiSearch.repository.MultiSearchRepository
import com.gabriel.domain.util.state.ResourceState

class MultiSearchRepositoryImpl(
    private val dataSource: MultiSearchDataSource,
    private val mapper: MultiDataMapper
) : MultiSearchRepository {
    override suspend fun searchMulti(query: String): ResourceState<List<MultiDomain>> {
        val resourceState = dataSource.searchMulti(query = query)
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
