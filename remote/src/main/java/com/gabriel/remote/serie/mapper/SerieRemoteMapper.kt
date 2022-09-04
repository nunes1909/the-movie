package com.gabriel.remote.serie.mapper

import com.gabriel.data.serie.model.SerieData
import com.gabriel.remote.serie.model.SerieResponse
import com.gabriel.remote.util.base.RemoteMapper

class SerieRemoteMapper : RemoteMapper<SerieResponse, SerieData> {
    override fun mapToData(type: SerieResponse): SerieData {
        return SerieData(
            id = type.id,
            title = type.title,
            background = type.background,
            banner = type.banner
        )
    }

    override fun mapFromRemote(type: SerieData): SerieResponse {
        return SerieResponse(
            id = type.id,
            title = type.title,
            background = type.background,
            banner = type.banner
        )
    }
}
