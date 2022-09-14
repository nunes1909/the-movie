package com.gabriel.themovie.movie.model

import com.gabriel.domain.genero.model.GeneroDomain
import java.io.Serializable

data class MovieView(
    val id: Int,
    val title: String,
    val description: String? = null,
    val type: String? = null,
    val nota: Double? = null,
    val generos: List<GeneroDomain>? = null,
    val favorito: Boolean? = false,
    val cartaz: String? = null,
    val banner: String? = null,
) : Serializable
