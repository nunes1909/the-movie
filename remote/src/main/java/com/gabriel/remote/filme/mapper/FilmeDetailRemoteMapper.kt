package com.gabriel.remote.filme.mapper

import com.gabriel.data.filme.model.FilmeData
import com.gabriel.remote.filme.model.FilmeDetailResponse
import com.gabriel.remote.filme.model.FilmeResponse
import com.gabriel.remote.genero.mapper.GeneroRemoteMapper
import com.gabriel.remote.util.base.RemoteMapper

class FilmeDetailRemoteMapper(private val mapper: GeneroRemoteMapper) :
    RemoteMapper<FilmeDetailResponse, FilmeData> {

    override fun mapToData(type: FilmeDetailResponse): FilmeData {
        val generosData = type.generos?.let {
            mapper.mapFromDataNonNull(it)
        } ?: listOf()

        return FilmeData(
            id = type.id,
            title = type.title,
            description = type.description,
            nota = type.nota,
            background = type.background,
            banner = type.banner,
            generos = generosData,
            favorito = false
        )
    }

    override fun mapFromData(type: FilmeData): FilmeDetailResponse {
        return FilmeDetailResponse(
            id = type.id,
            title = type.title
        )
    }
}
