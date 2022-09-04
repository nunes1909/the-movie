package com.gabriel.remote.filme.mapper

import com.gabriel.data.filme.model.FilmeData
import com.gabriel.remote.filme.model.FilmeResponse
import com.gabriel.remote.util.base.RemoteMapper

class FilmeRemoteMapper : RemoteMapper<FilmeResponse, FilmeData> {
    override fun mapToData(type: FilmeResponse): FilmeData {
        return FilmeData(
            id = type.id,
            title = type.title,
            background = type.background,
            banner = type.banner
        )
    }

    override fun mapFromRemote(type: FilmeData): FilmeResponse {
        return FilmeResponse(
            id = type.id,
            title = type.title,
            background = type.background,
            banner = type.banner
        )
    }
}
