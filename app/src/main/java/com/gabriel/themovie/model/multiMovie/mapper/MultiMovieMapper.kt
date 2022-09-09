package com.gabriel.themovie.model.multiMovie.mapper

import com.gabriel.themovie.model.filme.model.FilmeView
import com.gabriel.themovie.model.multiMovie.model.MultiMovie
import com.gabriel.themovie.util.base.ViewMapper

class MultiMovieMapper : ViewMapper<MultiMovie, FilmeView> {
    override fun mapToDomain(type: MultiMovie): FilmeView {
        return FilmeView(
            id = type.id,
            title = type.title
        )
    }

    override fun mapFromDomain(type: FilmeView): MultiMovie {
        return MultiMovie(
            id = type.id,
            title = type.title,
            type = type.type,
            banner = type.banner ?: "",
            favorito = type.favorito
        )
    }
}