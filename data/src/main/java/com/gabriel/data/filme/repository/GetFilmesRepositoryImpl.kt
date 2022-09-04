package com.gabriel.data.filme.repository

import com.gabriel.data.filme.dataSource.GetFilmesDataSource
import com.gabriel.data.filme.mapper.FilmeDataMapper
import com.gabriel.data.filme.model.FilmeData
import com.gabriel.data.util.state.ResourceState
import com.gabriel.domain.filme.model.FilmeDomain
import com.gabriel.domain.filme.repository.GetFilmesRepository

class GetFilmesRepositoryImpl(
    private val dataSource: GetFilmesDataSource,
    private val mapper: FilmeDataMapper
) : GetFilmesRepository {
    override suspend fun getAllFilmes(): ResourceState<List<FilmeDomain>> {
        val allFilmesData = dataSource.getAllFilmes().data
        mapper.mapFromDomainN(allFilmesData)
    }

    override suspend fun getFilterFilme(id: Int): ResourceState<FilmeData> {
        val filmeData = dataSource.getDetailFilme(id = query)
        return mapper.mapToDomain(filmeData)
    }
}
