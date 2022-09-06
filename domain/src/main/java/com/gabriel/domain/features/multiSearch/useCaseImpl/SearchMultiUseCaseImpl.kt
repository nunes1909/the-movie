package com.gabriel.domain.features.multiSearch.useCaseImpl

import com.gabriel.domain.features.multiSearch.model.MultiDomain
import com.gabriel.domain.features.multiSearch.repository.MultiSearchRepository
import com.gabriel.domain.features.multiSearch.useCase.SearchMultiUseCase
import com.gabriel.domain.util.state.ResourceState

class SearchMultiUseCaseImpl(private val repository: MultiSearchRepository) : SearchMultiUseCase {
    override suspend fun searchMulti(query: String): ResourceState<List<MultiDomain>> {
        return repository.searchMulti(query = query)
    }
}
