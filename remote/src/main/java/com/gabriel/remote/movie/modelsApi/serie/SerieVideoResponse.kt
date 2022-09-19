package com.gabriel.remote.movie.modelsApi.serie

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SerieVideoResponse(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("key")
    val key: String? = null,
    @SerializedName("official")
    val official: Boolean? = null,
) : Serializable
