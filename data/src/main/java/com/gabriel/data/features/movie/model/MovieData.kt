package com.gabriel.data.features.movie.model

import com.gabriel.domain.features.genero.model.GeneroDomain

data class MovieData(
    val id: Int,
    val title: String,
    val description: String? = null,
    val type: String? = null,
    val nota: Double? = null,
    val generos: List<GeneroDomain>? = null,
    val favorito: Boolean = false,
    val cartaz: String? = null,
    val banner: String? = null,
)
