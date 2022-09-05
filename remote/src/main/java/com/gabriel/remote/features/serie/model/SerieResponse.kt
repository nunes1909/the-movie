package com.gabriel.remote.features.serie.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SerieResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val title: String,
    @SerializedName("poster_path")
    val background: String? = null,
    @SerializedName("backdrop_path")
    val banner: String? = null
) : Serializable
