package com.gabriel.data.serie.mapper

import com.gabriel.data.genero.mapper.GeneroDataMapper
import com.gabriel.data.serie.model.SerieData
import com.gabriel.data.util.base.DataMapper
import com.gabriel.domain.serie.model.SerieDomain

class SerieDataMapper(private val mapper: GeneroDataMapper) : DataMapper<SerieData, SerieDomain> {
    override fun mapToDomain(type: SerieData): SerieDomain {
        val generosData = type.generos ?: listOf()
        val generosDomain = mapper.mapToDomain(generosData)
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

    override fun mapFromDomain(type: SerieDomain): SerieData {
        val generosDomain = type.generos ?: listOf()
        val generosData = mapper.mapFromDomain(generosDomain)
        return SerieData(
            id = type.id,
            title = type.title,
            description = type.description,
            nota = type.nota,
            background = type.background,
            banner = type.banner,
            generos = generosData,
            favorito = type.favorito
        )
    }
}
