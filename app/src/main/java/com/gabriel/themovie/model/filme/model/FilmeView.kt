package com.gabriel.themovie.model.filme.model

import com.gabriel.themovie.model.genero.model.GeneroView
import java.io.Serializable

data class FilmeView (
    val id: Int,
    val title: String,
    val description: String? = null,
    val nota: Double? = null,
    val banner: String? = null,
    val cartaz: String? = null,
    val generos: List<GeneroView?>? = null,
    val favorito: Boolean = false,
    val type: String = "",
) : Serializable
