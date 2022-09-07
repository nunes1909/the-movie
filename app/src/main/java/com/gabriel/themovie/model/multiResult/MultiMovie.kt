package com.gabriel.themovie.model.multiResult

import java.io.Serializable

data class MultiMovie(
    val id: Int,
    val title: String,
    val type: String,
    val banner: String,
    val favorito: Boolean = false
) : Serializable
