package com.gabriel.remote.features.multiSearch.mapper

import com.gabriel.data.features.multiSearch.model.MultiData
import com.gabriel.remote.features.genero.mapper.GeneroRemoteMapper
import com.gabriel.remote.features.multiSearch.model.MultiResponse
import com.gabriel.remote.util.base.RemoteMapper

class MultiRemoteMapper(private val mapper: GeneroRemoteMapper) :
    RemoteMapper<MultiResponse, MultiData> {
    override fun mapToData(type: MultiResponse): MultiData {
        val generosResponse = type.generos ?: listOf()
        val generosData = mapper.mapToData(generosResponse)
        return MultiData(
            id = type.id,
            name = type.name,
            banner = type.banner,
            description = type.description,
            nota = type.nota,
            generos = generosData,
            type = type.type,
            background = type.background,
            favorito = type.favorito
        )
    }

    override fun mapFromRemote(type: MultiData): MultiResponse {
        val generosData = type.generos ?: listOf()
        val generosResponse = mapper.mapFromRemote(generosData)
        return MultiResponse(
            id = type.id,
            name = type.name,
            banner = type.banner,
            description = type.description,
            nota = type.nota,
            generos = generosResponse,
            type = type.type,
            background = type.background,
            favorito = type.favorito
        )
    }
}
