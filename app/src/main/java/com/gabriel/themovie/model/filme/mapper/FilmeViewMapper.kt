package com.gabriel.themovie.model.filme.mapper

import com.gabriel.domain.features.filme.model.FilmeDomain
import com.gabriel.themovie.model.filme.model.FilmeView
import com.gabriel.themovie.model.genero.mapper.GeneroViewMapper
import com.gabriel.themovie.util.base.ViewMapper

class FilmeViewMapper(private val mapper: GeneroViewMapper) : ViewMapper<FilmeView, FilmeDomain> {
    override fun mapToDomain(type: FilmeView): FilmeDomain {
        val generosView = type.generos ?: listOf()
        val generosDomain = mapper.mapToDomain(generosView)
        return FilmeDomain(
            id = type.id,
            title = type.title,
            description = type.description,
            nota = type.nota,
            background = type.banner,
            banner = type.cartaz,
            generos = generosDomain,
            favorito = type.favorito
        )
    }

    override fun mapFromDomain(type: FilmeDomain): FilmeView {
        val generosDomain = type.generos ?: listOf()
        val generosView = mapper.mapFromDomain(generosDomain)
        return FilmeView(
            id = type.id,
            title = type.title,
            description = type.description,
            nota = type.nota,
            banner = type.background,
            cartaz = type.banner,
            generos = generosView,
            favorito = type.favorito
        )
    }
}