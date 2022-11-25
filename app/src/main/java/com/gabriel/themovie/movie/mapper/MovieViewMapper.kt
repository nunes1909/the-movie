package com.gabriel.themovie.movie.mapper

import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.themovie.genero.mapper.GeneroViewMapper
import com.gabriel.themovie.movie.model.MovieView
import com.gabriel.themovie.util.base.ViewMapper
import com.gabriel.themovie.video.mapper.VideoViewMapper

class MovieViewMapper(
    private val generoMapper: GeneroViewMapper,
    private val videoMapper: VideoViewMapper
) : ViewMapper<MovieView, MovieDomain> {
    override fun mapToDomain(type: MovieView): MovieDomain {
        val generos = type.generos?.let {
            generoMapper.mapToDomainNonNull(it)
        } ?: listOf()

        val videos = type.videos?.let {
            videoMapper.mapToDomainNonNull(it)
        } ?: listOf()

        return MovieDomain(
            id = type.id,
            title = type.title,
            description = type.description,
            type = type.type,
            nota = type.nota,
            favorito = type.favorito,
            generos = generos,
            videos = videos,
            cartaz = type.cartaz,
            banner = type.banner,
            usuarioId = type.usuarioId
        )
    }

    override fun mapToView(type: MovieDomain): MovieView {
        val generos = type.generos?.let {
            generoMapper.mapToViewNonNull(it)
        } ?: listOf()

        val videos = type.videos?.let {
            videoMapper.mapToViewNonNull(it)
        } ?: listOf()

        return MovieView(
            id = type.id,
            title = type.title,
            description = type.description,
            type = type.type,
            nota = type.nota,
            favorito = type.favorito,
            generos = generos,
            videos = videos,
            cartaz = type.cartaz,
            banner = type.banner,
            usuarioId = type.usuarioId
        )
    }
}
