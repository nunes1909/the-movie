package com.gabriel.cache.features.serie.model

import com.gabriel.cache.features.genero.model.GeneroCache
import java.io.Serializable

data class SeriesCache(
    val id: Int,
    val title: String,
    val description: String? = null,
    val nota: Double? = null,
    val background: String? = null,
    val banner: String? = null,
    val generos: List<GeneroCache?>? = null,
    val favorito: Boolean = false
) : Serializable