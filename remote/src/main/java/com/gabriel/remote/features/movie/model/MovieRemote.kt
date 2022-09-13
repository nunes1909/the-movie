package com.gabriel.remote.features.movie.model

import com.gabriel.domain.features.genero.model.GeneroDomain

data class MovieRemote(
    val id: Int,
    val title: String,
    val description: String,
    val type: String? = null,
    val nota: Double? = null,
    val generos: List<GeneroDomain>? = null,
    val favorito: Boolean = false,
    val cartaz: String,
    val banner: String,
)
