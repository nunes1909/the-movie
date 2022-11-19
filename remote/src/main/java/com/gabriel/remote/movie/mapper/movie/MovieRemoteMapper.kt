package com.gabriel.remote.movie.mapper.movie

import com.gabriel.data.movie.model.MovieData
import com.gabriel.remote.movie.model.MovieRemote
import com.gabriel.remote.util.base.RemoteMapper

class MovieRemoteMapper : RemoteMapper<MovieData, MovieRemote> {
    override fun mapToData(type: MovieData): MovieRemote {
        return MovieRemote(
            id = type.id,
            title = type.title,
            description = type.description,
            type = type.type,
            cartaz = type.cartaz,
            usuarioId = type.usuarioId
        )
    }

    override fun mapToRemote(type: MovieRemote): MovieData {
        return MovieData(
            id = type.id,
            title = type.title,
            description = type.description,
            type = type.type,
            cartaz = type.cartaz,
            usuarioId = type.usuarioId
        )
    }
}