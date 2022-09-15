package com.gabriel.themovie.movie.mapper

import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.themovie.genero.mapper.GeneroViewMapper
import com.gabriel.themovie.movie.model.MovieView
import com.gabriel.themovie.util.base.ViewMapper

class MovieViewMapper(private val mapper: GeneroViewMapper) : ViewMapper<MovieView, MovieDomain> {
    override fun mapToDomain(type: MovieView): MovieDomain {
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

    override fun mapToView(type: MovieDomain): MovieView {
        val generos = type.generos?.let {
            mapper.mapToViewNonNull(it)
        } ?: listOf()

        return MovieView(
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
}
