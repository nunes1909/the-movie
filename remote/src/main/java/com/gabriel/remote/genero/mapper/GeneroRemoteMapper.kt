package com.gabriel.remote.genero.mapper

import com.gabriel.data.genero.model.GeneroData
import com.gabriel.remote.genero.model.GeneroResponse
import com.gabriel.remote.util.base.RemoteMapper

class GeneroRemoteMapper : RemoteMapper<GeneroResponse, GeneroData> {
    override fun mapToData(type: GeneroResponse): GeneroData {
        return GeneroData(
            id = type.id,
            name = type.name
        )
    }

    override fun mapToRemote(type: GeneroData): GeneroResponse {
        return GeneroResponse(
            id = type.id,
            name = type.name
        )
    }
}
