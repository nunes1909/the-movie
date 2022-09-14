package com.gabriel.remote.movie.mapper.filme

import com.gabriel.data.movie.model.MovieData
import com.gabriel.remote.movie.modelsApi.filme.FilmeDetailResponse
import com.gabriel.remote.util.base.RemoteMapper

class FilmeDetailResponseToDataMapper : RemoteMapper<FilmeDetailResponse, MovieData> {
    override fun mapToData(type: FilmeDetailResponse): MovieData {
        return MovieData(
            id = type.id,
            title = type.title,
            description = type.description,
            cartaz = type.cartaz,
            banner = type.banner,
            nota = type.nota,
            favorito = type.favorito
        )
    }

    override fun mapFromRemote(type: MovieData): FilmeDetailResponse {
        return FilmeDetailResponse(
            id = type.id,
            title = type.title,
            cartaz = type.cartaz,
            banner = type.banner
        )
    }
}
