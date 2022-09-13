package com.gabriel.remote.features.movie.mapper.filme

import com.gabriel.data.features.movie.model.MovieData
import com.gabriel.remote.features.movie.modelsApi.filme.FilmeResponse
import com.gabriel.remote.util.base.RemoteMapper

class FilmeResponseToDataMapper : RemoteMapper<FilmeResponse, MovieData> {
    override fun mapToData(type: FilmeResponse): MovieData {
        return MovieData(
            id = type.id,
            title = type.title,
            cartaz = type.cartaz,
            banner = type.banner
        )
    }

    override fun mapFromRemote(type: MovieData): FilmeResponse {
        return FilmeResponse(
            id = type.id,
            title = type.title,
            cartaz = type.cartaz,
            banner = type.banner
        )
    }
}
