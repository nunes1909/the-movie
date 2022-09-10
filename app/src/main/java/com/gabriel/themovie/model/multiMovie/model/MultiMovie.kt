package com.gabriel.themovie.model.multiMovie.model

import com.gabriel.themovie.model.genero.model.GeneroView
import java.io.Serializable

data class MultiMovie(
    val id: Int,
    val title: String,
    val description: String?,
    val type: String,
    val nota: Double?,
    val banner: String?,
    val generos: List<GeneroView?>? = null,
    val cartaz: String?,
    val favorito: Boolean = false
) : Serializable
