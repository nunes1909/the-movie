package com.gabriel.domain.features.multiSearch.model

import com.gabriel.domain.features.genero.model.GeneroDomain
import java.io.Serializable

data class MultiDomain(
    val id: Int,
    val name: String,
    val banner: String? = null,
    val description: String? = null,
    val nota: Double? = null,
    val generos: List<GeneroDomain?>? = null,
    val type: String? = null,
    val background: String? = null,
    val favorito: Boolean? = false
) : Serializable
