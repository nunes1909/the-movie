package com.gabriel.themovie.model.multiMovie

import java.io.Serializable

data class MultiMovie(
    val id: Int,
    val title: String,
    val type: String,
    val banner: String,
    val favorito: Boolean = false
) : Serializable
