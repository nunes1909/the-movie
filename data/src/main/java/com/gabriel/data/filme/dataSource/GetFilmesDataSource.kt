package com.gabriel.data.filme.dataSource

import com.gabriel.data.filme.model.FilmeData
import com.gabriel.data.util.state.ResourceState

interface GetFilmesDataSource {
    suspend fun getAllFilmes(): ResourceState<List<FilmeData>>
    suspend fun getDetailFilme(filmeId: Int): ResourceState<FilmeData>
}
