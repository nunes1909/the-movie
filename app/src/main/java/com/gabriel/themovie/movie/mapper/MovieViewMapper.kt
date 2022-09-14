package com.gabriel.themovie.movie.mapper

import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.themovie.movie.model.MovieView
import com.gabriel.themovie.util.base.ViewMapper

class MovieViewMapper : ViewMapper<MovieView, MovieDomain> {
    override fun mapToDomain(type: MovieView): MovieDomain {
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

    override fun mapFromDomain(type: MovieDomain): MovieView {
        return MovieView(
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