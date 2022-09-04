package com.gabriel.domain.filme.model

import com.gabriel.domain.genero.model.GeneroDomain
import java.io.Serializable

data class FilmeDomain(
    val id: Int,
    val title: String,
    val description: String? = null,
    val nota: Double? = null,
    val background: String? = null,
    val banner: String? = null,
    val generos: List<GeneroDomain?>? = null,
    val favorito: Boolean = false
) : Serializable
