package com.gabriel.data.movie.mapper

import com.gabriel.data.genero.mapper.GeneroDataMapper
import com.gabriel.data.movie.model.MovieData
import com.gabriel.data.util.base.DataMapper
import com.gabriel.data.video.mapper.VideoDataMapper
import com.gabriel.domain.movie.model.MovieDomain

class MovieDataMapper(
    private val generoMapper: GeneroDataMapper,
    private val videoMapper: VideoDataMapper
) : DataMapper<MovieData, MovieDomain> {
    override fun mapToDomain(type: MovieData): MovieDomain {
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
            generos = generos,
            videos = videos,
            favorito = type.favorito,
            cartaz = type.cartaz,
            banner = type.banner,
            usuarioId = type.usuarioId
        )
    }

    override fun mapToData(type: MovieDomain): MovieData {
        val generos = type.generos?.let {
            generoMapper.mapToDataNonNull(it)
        } ?: listOf()

        val videos = type.videos?.let {
            videoMapper.mapToDataNonNull(it)
        } ?: listOf()

        return MovieData(
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
