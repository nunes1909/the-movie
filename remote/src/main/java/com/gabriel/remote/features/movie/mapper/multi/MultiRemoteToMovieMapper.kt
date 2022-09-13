package com.gabriel.remote.features.movie.mapper.multi

import com.gabriel.data.features.movie.model.MovieData
import com.gabriel.remote.features.movie.modelsApi.multi.MultiResponse
import com.gabriel.remote.util.base.RemoteMapper

class MultiRemoteToMovieMapper() :
    RemoteMapper<MultiResponse, MovieData> {
    override fun mapToData(type: MultiResponse): MovieData {
        return MovieData(
            id = type.id,
            title = type.title,
            description = type.description,
            type = type.type,
            nota = type.nota,
            favorito = type.favorito,
            cartaz = type.cartaz,
            banner = type.banner
        )
    }

    override fun mapFromRemote(type: MovieData): MultiResponse {
        return MultiResponse(
            id = type.id,
            title = type.title,
            description = type.description,
            type = type.type,
            nota = type.nota,
            favorito = type.favorito,
            cartaz = type.cartaz,
            banner = type.banner
        )
    }
}
