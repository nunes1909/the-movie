package com.gabriel.remote.movie.modelsApi.serie

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SerieResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val title: String,
    @SerializedName("overview")
    val description: String? = null,
    @SerializedName("poster_path")
    val cartaz: String? = null,
    @SerializedName("backdrop_path")
    val banner: String? = null
) : Serializable
