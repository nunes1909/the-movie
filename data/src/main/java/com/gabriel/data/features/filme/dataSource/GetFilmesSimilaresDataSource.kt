package com.gabriel.data.features.filme.dataSource

import com.gabriel.data.features.filme.model.FilmeData
import com.gabriel.domain.util.state.ResourceState

interface GetFilmesSimilaresDataSource {
    suspend fun getFilmesSimilares(filmeId: Int): ResourceState<List<FilmeData>>
}
