package com.gabriel.remote.features.serie.mapper

import com.gabriel.data.features.serie.model.SerieData
import com.gabriel.remote.features.serie.model.SerieResponse
import com.gabriel.remote.util.base.RemoteMapper

class SerieRemoteMapper : RemoteMapper<SerieResponse, SerieData> {
    override fun mapToData(type: SerieResponse): SerieData {
        return SerieData(
            id = type.id,
            title = type.title,
            background = type.cartaz,
            banner = type.banner
        )
    }

    override fun mapFromRemote(type: SerieData): SerieResponse {
        return SerieResponse(
            id = type.id,
            title = type.title,
            cartaz = type.background,
            banner = type.banner
        )
    }
}
