package com.gabriel.themovie.model.serie.mapper

import com.gabriel.domain.features.serie.model.SerieDomain
import com.gabriel.themovie.model.genero.mapper.GeneroViewMapper
import com.gabriel.themovie.model.serie.model.SerieView
import com.gabriel.themovie.util.base.ViewMapper

class SerieViewMapper(private val mapper: GeneroViewMapper) : ViewMapper<SerieView, SerieDomain> {
    override fun mapToDomain(type: SerieView): SerieDomain {
        val generosView = type.generos ?: listOf()
        val generosDomain = mapper.mapToDomain(generosView)
        return SerieDomain(
            id = type.id,
            title = type.title,
            description = type.description,
            nota = type.nota,
            background = type.background,
            banner = type.banner,
            generos = generosDomain,
            favorito = type.favorito
        )
    }

    override fun mapFromDomain(type: SerieDomain): SerieView {
        val generosDomain = type.generos ?: listOf()
        val generosView = mapper.mapFromDomain(generosDomain)
        return SerieView(
            id = type.id,
            title = type.title,
            description = type.description,
            nota = type.nota,
            background = type.background,
            banner = type.banner,
            generos = generosView,
            favorito = type.favorito
        )
    }
}
