package com.gabriel.themovie.model.serie.model

import com.gabriel.themovie.model.genero.model.GeneroView
import com.gabriel.themovie.util.constants.ConstantsView.TYPE_SERIE
import java.io.Serializable

data class SerieView(
    val id: Int,
    val title: String,
    val description: String? = null,
    val nota: Double? = null,
    val background: String? = null,
    val banner: String? = null,
    val generos: List<GeneroView?>? = null,
    val favorito: Boolean = false,
    val type: String = TYPE_SERIE
) : Serializable
