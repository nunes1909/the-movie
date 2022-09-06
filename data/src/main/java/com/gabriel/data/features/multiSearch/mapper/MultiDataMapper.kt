package com.gabriel.data.features.multiSearch.mapper

import com.gabriel.data.features.genero.mapper.GeneroDataMapper
import com.gabriel.data.features.multiSearch.model.MultiData
import com.gabriel.data.util.base.DataMapper
import com.gabriel.domain.features.multiSearch.model.MultiDomain

class MultiDataMapper(private val mapper: GeneroDataMapper) : DataMapper<MultiData, MultiDomain> {
    override fun mapToDomain(type: MultiData): MultiDomain {
        val generosData = type.generos ?: listOf()
        val generosDomain = mapper.mapToDomain(generosData)
        return MultiDomain(
            id = type.id,
            name = type.name,
            banner = type.banner,
            description = type.description,
            nota = type.nota,
            generos = generosDomain,
            type = type.type,
            background = type.background,
            favorito = type.favorito
        )
    }

    override fun mapFromDomain(type: MultiDomain): MultiData {
        val generosData = type.generos ?: listOf()
        val generosDomain = mapper.mapFromDomain(generosData)
        return MultiData(
            id = type.id,
            name = type.name,
            banner = type.banner,
            description = type.description,
            nota = type.nota,
            generos = generosDomain,
            type = type.type,
            background = type.background,
            favorito = type.favorito
        )
    }
}