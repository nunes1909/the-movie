package com.gabriel.data.features.multiSearch.model

import com.gabriel.data.features.genero.model.GeneroData
import java.io.Serializable

data class MultiData(
    val id: Int,
    val name: String,
    val banner: String? = null,
    val description: String? = null,
    val nota: Double? = null,
    val generos: List<GeneroData?>? = null,
    val type: String? = null,
    val background: String? = null,
    val favorito: Boolean? = false
) : Serializable
