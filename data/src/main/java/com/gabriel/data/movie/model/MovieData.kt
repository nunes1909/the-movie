package com.gabriel.data.movie.model

import com.gabriel.data.genero.model.GeneroData
import com.gabriel.data.video.model.VideoData

data class MovieData(
    val id: Int,
    val title: String,
    var description: String? = null,
    val type: String? = null,
    val nota: Double? = null,
    val generos: List<GeneroData>? = null,
    var videos: List<VideoData>? = null,
    val favorito: Boolean? = false,
    val cartaz: String? = null,
    val banner: String? = null,
    val usuarioId: String? = null
)
