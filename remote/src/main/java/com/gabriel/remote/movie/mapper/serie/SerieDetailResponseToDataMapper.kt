package com.gabriel.remote.movie.mapper.serie

import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.constants.ConstantsDomain.TYPE_SERIE
import com.gabriel.remote.movie.modelsApi.serie.SerieDetailResponse
import com.gabriel.remote.util.base.RemoteMapper

class SerieDetailResponseToDataMapper : RemoteMapper<SerieDetailResponse, MovieData> {
    override fun mapToData(type: SerieDetailResponse): MovieData {
        return MovieData(
            id = type.id,
            title = type.title,
            description = type.description,
            cartaz = type.cartaz,
            type = TYPE_SERIE,
            banner = type.banner,
            nota = type.nota,
            favorito = type.favorito
        )
    }

    override fun mapToRemote(type: MovieData): SerieDetailResponse {
        return SerieDetailResponse(
            id = type.id,
            title = type.title,
            cartaz = type.cartaz,
            banner = type.banner
        )
    }
}
