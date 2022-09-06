package com.gabriel.data.features.filme.dataSource

import com.gabriel.data.features.filme.model.FilmeData
import com.gabriel.domain.util.state.ResourceState

interface GetFilmesDataSource {
    suspend fun getAllFilmes(): ResourceState<List<FilmeData>>
    suspend fun getDetailFilme(filmeId: Int): ResourceState<FilmeData>
}
