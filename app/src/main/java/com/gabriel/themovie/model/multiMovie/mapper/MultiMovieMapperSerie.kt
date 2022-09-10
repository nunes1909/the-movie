package com.gabriel.themovie.model.multiMovie.mapper

import com.gabriel.themovie.model.multiMovie.model.MultiMovie
import com.gabriel.themovie.model.serie.model.SerieView
import com.gabriel.themovie.util.base.ViewMapper
import com.gabriel.themovie.util.constants.ConstantsView

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
            type = ConstantsView.TYPE_FILME,
            nota = type.nota,
            banner = type.banner,
            generos = type.generos,
            cartaz = type.background,
            favorito = type.favorito
        )
    }
}