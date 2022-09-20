package com.gabriel.themovie.video.model

import java.io.Serializable

data class VideoView(
    val id: String? = null,
    val key: String? = null,
    val type: String? = null,
    val official: Boolean? = null,
) : Serializable
