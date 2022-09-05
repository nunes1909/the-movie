package com.gabriel.remote.features.genero.mapper

import com.gabriel.data.features.genero.model.GeneroData
import com.gabriel.remote.features.genero.model.GeneroResponse
import com.gabriel.remote.util.base.RemoteMapper

class GeneroRemoteMapper : RemoteMapper<GeneroResponse, GeneroData> {
    override fun mapToData(type: GeneroResponse): GeneroData {
        return GeneroData(
            id = type.id,
            name = type.name
        )
    }

    override fun mapFromRemote(type: GeneroData): GeneroResponse {
        return GeneroResponse(
            id = type.id,
            name = type.name
        )
    }
}
