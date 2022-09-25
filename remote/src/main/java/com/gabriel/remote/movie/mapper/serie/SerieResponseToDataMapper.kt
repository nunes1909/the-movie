package com.gabriel.remote.movie.mapper.serie

import com.gabriel.data.movie.model.MovieData
import com.gabriel.remote.movie.modelsApi.serie.SerieResponse
import com.gabriel.remote.util.base.RemoteMapper

class SerieResponseToDataMapper : RemoteMapper<SerieResponse, MovieData> {
    override fun mapToData(type: SerieResponse): MovieData {
        return MovieData(
            id = type.id,
            title = type.title,
            description = type.description,
            cartaz = type.cartaz,
            banner = type.banner
        )
    }

    override fun mapToRemote(type: MovieData): SerieResponse {
        return SerieResponse(
            id = type.id,
            title = type.title,
            description = type.description,
            cartaz = type.cartaz,
            banner = type.banner
        )
    }
}
