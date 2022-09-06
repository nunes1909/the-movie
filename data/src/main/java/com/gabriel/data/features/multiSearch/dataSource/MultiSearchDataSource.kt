package com.gabriel.data.features.multiSearch.dataSource

import com.gabriel.data.features.multiSearch.model.MultiData
import com.gabriel.domain.util.state.ResourceState

interface MultiSearchDataSource {
    suspend fun searchMulti(query: String): ResourceState<List<MultiData>>
}
