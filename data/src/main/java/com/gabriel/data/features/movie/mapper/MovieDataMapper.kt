package com.gabriel.data.features.movie.mapper

import com.gabriel.data.features.movie.model.MovieData
import com.gabriel.data.util.base.DataMapper
import com.gabriel.domain.features.movie.model.MovieDomain

class MovieDataMapper : DataMapper<MovieData, MovieDomain> {
    override fun mapToDomain(type: MovieData): MovieDomain {
        return MovieDomain(
            id = type.id,
            title = type.title,
            description = type.description,
            type = type.type,
            nota = type.nota,
            favorito = type.favorito,
            cartaz = type.cartaz,
            banner = type.banner
        )
    }

    override fun mapFromDomain(type: MovieDomain): MovieData {
        return MovieData(
            id = type.id,
            title = type.title,
            description = type.description,
            type = type.type,
            nota = type.nota,
            favorito = type.favorito,
            cartaz = type.cartaz,
            banner = type.banner
        )
    }
}
