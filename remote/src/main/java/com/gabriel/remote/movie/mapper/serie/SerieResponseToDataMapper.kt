package com.gabriel.remote.movie.mapper.serie

import com.gabriel.data.movie.model.MovieData
import com.gabriel.remote.movie.modelsApi.serie.SerieResponse
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