package com.gabriel.remote.features.movie.mapper

import com.gabriel.data.features.movie.model.MovieData
import com.gabriel.remote.features.movie.modelsApi.filme.FilmeResponse
import com.gabriel.remote.features.serie.model.SerieResponse
import com.gabriel.remote.util.base.RemoteMapper

class SerieResponseToDataMapper : RemoteMapper<SerieResponse, MovieData> {
    override fun mapToData(type: SerieResponse): MovieData {
        return MovieData(
            id = type.id,
            title = type.title,
            cartaz = type.cartaz,
            banner = type.banner
        )
    }

    override fun mapFromRemote(type: MovieData): SerieResponse {
        return SerieResponse(
            id = type.id,
            title = type.title,
            cartaz = type.cartaz,
            banner = type.banner
        )
    }
}
