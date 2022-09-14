package com.gabriel.data.movie.model

import com.gabriel.data.genero.model.GeneroData

data class MovieData(
    val id: Int,
    val title: String,
    val description: String? = null,
    val type: String? = null,
    val nota: Double? = null,
    val generos: List<GeneroData>? = null,
    val favorito: Boolean? = false,
    val cartaz: String? = null,
    val banner: String? = null
)