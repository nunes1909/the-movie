package com.gabriel.remote.movie.mapper.filme

import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.constants.ConstantsDomain.TYPE_FILME
import com.gabriel.remote.genero.mapper.GeneroRemoteMapper
import com.gabriel.remote.movie.modelsApi.filme.FilmeDetailResponse
import com.gabriel.remote.util.base.RemoteMapper

class FilmeDetailResponseToDataMapper(
    private val mapper: GeneroRemoteMapper
) : RemoteMapper<FilmeDetailResponse, MovieData> {
    override fun mapToData(type: FilmeDetailResponse): MovieData {
        val generos = type.generos?.let {
            mapper.mapFromDomainNonNull(it)
        } ?: listOf()

        return MovieData(
            id = type.id,
            title = type.title,
            description = type.description,
            cartaz = type.cartaz,
            type = TYPE_FILME,
            banner = type.banner,
            nota = type.nota,
            generos = generos,
            favorito = type.favorito
        )
    }

    override fun mapFromRemote(type: MovieData): FilmeDetailResponse {
        val generos = type.generos?.let {
            mapper.mapToDomainNonNull(it)
        } ?: listOf()

        return FilmeDetailResponse(
            id = type.id,
            title = type.title,
            description = type.description,
            cartaz = type.cartaz,
            banner = type.banner,
            nota = type.nota,
            generos = generos,
            favorito = type.favorito
        )
    }
}
