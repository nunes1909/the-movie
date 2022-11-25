package com.gabriel.themovie.movie.model

import com.gabriel.themovie.genero.model.GeneroView
import com.gabriel.themovie.video.model.VideoView
import java.io.Serializable

data class MovieView(
    val id: Int,
    val title: String,
    val description: String? = null,
    var type: String? = null,
    val nota: Double? = null,
    val generos: List<GeneroView>? = null,
    val videos: List<VideoView>? = null,
    val favorito: Boolean? = false,
    val cartaz: String? = null,
    val banner: String? = null,
    val usuarioId: String? = null
) : Serializable
