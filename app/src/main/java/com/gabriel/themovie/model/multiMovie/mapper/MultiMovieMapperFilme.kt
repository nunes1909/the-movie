package com.gabriel.themovie.model.multiMovie.mapper

import com.gabriel.themovie.model.filme.model.FilmeView
import com.gabriel.themovie.model.multiMovie.model.MultiMovie
import com.gabriel.themovie.util.base.ViewMapper
import com.gabriel.themovie.util.constants.ConstantsView

class MultiMovieMapperFilme : ViewMapper<MultiMovie, FilmeView> {
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
            description = type.description,
            type = ConstantsView.TYPE_FILME,
            nota = type.nota,
            banner = type.banner,
            generos = type.generos,
            cartaz = type.cartaz,
            favorito = type.favorito
        )
    }
}