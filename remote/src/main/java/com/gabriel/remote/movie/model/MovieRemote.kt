package com.gabriel.remote.movie.model

data class MovieRemote(
    val id: Int = 0,
    val title: String = "",
    val description: String? = null,
    val type: String? = null,
    val cartaz: String? = null,
    var usuarioId: String? = null
)
