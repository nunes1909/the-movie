package com.gabriel.remote.movie.mapper.filme

import com.gabriel.data.movie.model.MovieData
import com.gabriel.remote.movie.modelsApi.filme.FilmeResponse
import com.gabriel.remote.util.base.RemoteMapper

class FilmeResponseToDataMapper : RemoteMapper<FilmeResponse, MovieData> {
    override fun mapToData(type: FilmeResponse): MovieData {
        return MovieData(
            id = type.id,
            title = type.title,
            description = type.description,
            cartaz = type.cartaz,
            banner = type.banner
        )
    }

    override fun mapToRemote(type: MovieData): FilmeResponse {
        return FilmeResponse(
            id = type.id,
            title = type.title,
            description = type.description,
            cartaz = type.cartaz,
            banner = type.banner
        )
    }
}
