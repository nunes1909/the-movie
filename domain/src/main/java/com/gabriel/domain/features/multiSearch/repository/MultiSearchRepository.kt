package com.gabriel.domain.features.multiSearch.repository

import com.gabriel.domain.features.multiSearch.model.MultiDomain
import com.gabriel.domain.util.state.ResourceState

interface MultiSearchRepository {
    suspend fun searchMulti(query: String): ResourceState<List<MultiDomain>>
}
