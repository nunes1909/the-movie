package com.gabriel.domain.features.multiSearch.useCase

import com.gabriel.domain.features.multiSearch.model.MultiDomain
import com.gabriel.domain.util.state.ResourceState

interface SearchMultiUseCase {
    suspend fun searchMulti(query: String): ResourceState<MultiDomain>
}
