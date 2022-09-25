package com.gabriel.remote.movie.mapper.multi

import com.gabriel.data.movie.model.MovieData
import com.gabriel.remote.movie.modelsApi.multi.MultiResponse
import com.gabriel.remote.util.base.RemoteMapper

class MultiRemoteToMovieMapper : RemoteMapper<MultiResponse, MovieData> {
    override fun mapToData(type: MultiResponse): MovieData {
        return MovieData(
            id = type.id,
            title = type.title,
            description = type.description,
            cartaz = type.cartaz,
            banner = type.banner
        )
    }

    override fun mapToRemote(type: MovieData): MultiResponse {
        return MultiResponse(
            id = type.id,
            title = type.title,
            description = type.description,
            cartaz = type.cartaz,
            banner = type.banner
        )
    }
}
