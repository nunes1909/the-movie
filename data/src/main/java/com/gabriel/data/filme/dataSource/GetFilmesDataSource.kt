package com.gabriel.data.filme.dataSource

import com.gabriel.data.filme.model.FilmeData

interface GetFilmesDataSource {
    suspend fun getAllFilmes(): List<FilmeData>
    suspend fun getFilterFilme(query: String): FilmeData
}
