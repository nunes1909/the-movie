package com.gabriel.themovie.model.multiMovie.mapper

import com.gabriel.themovie.model.multiMovie.model.MultiMovie
import com.gabriel.themovie.model.serie.model.SerieView
import com.gabriel.themovie.util.base.ViewMapper
import com.gabriel.themovie.util.constants.ConstantsView.TYPE_SERIE

class MultiMovieMapperSerie : ViewMapper<MultiMovie, SerieView> {
    override fun mapToDomain(type: MultiMovie): SerieView {
        return SerieView(
            id = type.id,
            title = type.title
        )
    }

    override fun mapFromDomain(type: SerieView): MultiMovie {
        return MultiMovie(
            id = type.id,
            title = type.title,
            description = type.description,
            type = TYPE_SERIE,
            nota = type.nota,
            banner = type.banner,
            generos = type.generos,
            cartaz = type.background,
            favorito = type.favorito
        )
    }
}