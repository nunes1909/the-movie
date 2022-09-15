package com.gabriel.data.movie.mapper

import com.gabriel.data.genero.mapper.GeneroDataMapper
import com.gabriel.data.movie.model.MovieData
import com.gabriel.data.util.base.DataMapper
import com.gabriel.domain.movie.model.MovieDomain

class MovieDataMapper(private val mapper: GeneroDataMapper) : DataMapper<MovieData, MovieDomain> {
    override fun mapToDomain(type: MovieData): MovieDomain {
        val generos = type.generos?.let {
            mapper.mapToDomainNonNull(it)
        } ?: listOf()

        return MovieDomain(
            id = type.id,
            title = type.title,
            description = type.description,
            type = type.type,
            nota = type.nota,
            generos = generos,
            favorito = type.favorito,
            cartaz = type.cartaz,
            banner = type.banner
        )
    }

    override fun mapToData(type: MovieDomain): MovieData {
        val generos = type.generos?.let {
            mapper.mapToDataNonNull(it)
        } ?: listOf()

        return MovieData(
            id = type.id,
            title = type.title,
            description = type.description,
            type = type.type,
            nota = type.nota,
            favorito = type.favorito,
            generos = generos,
            cartaz = type.cartaz,
            banner = type.banner
        )
    }
}
