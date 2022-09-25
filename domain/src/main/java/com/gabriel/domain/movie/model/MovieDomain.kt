package com.gabriel.domain.movie.model

import com.gabriel.domain.genero.model.GeneroDomain
import com.gabriel.domain.video.model.VideoDomain

data class MovieDomain(
    val id: Int,
    val title: String,
    val description: String? = null,
    val type: String? = null,
    val nota: Double? = null,
    val generos: List<GeneroDomain>? = null,
    val videos: List<VideoDomain>? = null,
    val favorito: Boolean? = false,
    val cartaz: String? = null,
    val banner: String? = null,
)
