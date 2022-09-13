package com.gabriel.remote.features.serie.mapper

import com.gabriel.data.features.serie.model.SerieData
import com.gabriel.remote.features.genero.mapper.GeneroRemoteMapper
import com.gabriel.remote.features.serie.model.SerieDetailResponse
import com.gabriel.remote.util.base.RemoteMapper

class SerieDetailRemoteMapper(private val mapper: GeneroRemoteMapper) :
    RemoteMapper<SerieDetailResponse, SerieData> {
    override fun mapToData(type: SerieDetailResponse): SerieData {
        val generosResponse = type.generos ?: listOf()
        val generosData = mapper.mapToData(generosResponse)
        return SerieData(
            id = type.id,
            title = type.title,
            description = type.description,
            nota = type.nota,
            background = type.cartaz,
            banner = type.banner,
            generos = generosData,
            favorito = type.favorito
        )
    }

    override fun mapFromRemote(type: SerieData): SerieDetailResponse {
        return SerieDetailResponse(
            id = type.id,
            title = type.title
        )
    }
}