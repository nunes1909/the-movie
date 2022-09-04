package com.gabriel.data.filme.model

import com.gabriel.data.genero.model.GeneroData
import java.io.Serializable

data class FilmeData(
    val id: Int,
    val title: String,
    val description: String? = null,
    val nota: Double? = null,
    val background: String? = null,
    val banner: String? = null,
    val generos: List<GeneroData?>? = null,
    val favorito: Boolean = false
) : Serializable
