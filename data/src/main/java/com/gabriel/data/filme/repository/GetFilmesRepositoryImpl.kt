package com.gabriel.data.filme.repository

import com.gabriel.data.filme.dataSource.GetFilmesDataSource
import com.gabriel.data.filme.mapper.FilmeDataMapper
import com.gabriel.domain.filme.model.FilmeDomain
import com.gabriel.domain.filme.repository.GetFilmesRepository

class GetFilmesRepositoryImpl(
    private val dataSource: GetFilmesDataSource,
    private val mapper: FilmeDataMapper
) : GetFilmesRepository {
    override suspend fun getAllFilmes(): List<FilmeDomain> {
        val allFilmesData = dataSource.getAllFilmes()
        return mapper.mapFromDomainNonNull(allFilmesData)
    }

    override suspend fun getFilterFilme(query: String): FilmeDomain {
        val filmeData = dataSource.getFilterFilme(query = query)
        return mapper.mapToDomain(filmeData)
    }
}
