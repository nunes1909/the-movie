package com.gabriel.data.filme.mapper

import com.gabriel.data.filme.model.FilmeData
import com.gabriel.data.genero.mapper.GeneroDataMapper
import com.gabriel.data.util.base.DataMapper
import com.gabriel.domain.filme.model.FilmeDomain

class FilmeDataMapper(private val mapper: GeneroDataMapper) : DataMapper<FilmeData, FilmeDomain> {
    override fun mapToDomain(type: FilmeData): FilmeDomain {
        val generosDomain = type.generos ?: listOf()
        return FilmeDomain(
            id = type.id,
            title = type.title,
            description = type.description,
            nota = type.nota,
            background = type.background,
            banner = type.banner,
            generos = mapper.mapToDomain(generosDomain),
            favorito = type.favorito
        )
    }

    override fun mapFromDomain(type: FilmeDomain): FilmeData {
        val generosData = type.generos ?: listOf()
        return FilmeData(
            id = type.id,
            title = type.title,
            description = type.description,
            nota = type.nota,
            background = type.background,
            banner = type.banner,
            generos = mapper.mapFromDomain(generosData),
            favorito = type.favorito
        )
    }
}
