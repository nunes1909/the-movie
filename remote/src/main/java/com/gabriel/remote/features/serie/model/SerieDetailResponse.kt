package com.gabriel.remote.features.serie.model

import com.gabriel.remote.features.genero.model.GeneroResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SerieDetailResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val title: String,
    @SerializedName("overview")
    val description: String,
    @SerializedName("vote_average")
    val nota: Double,
    @SerializedName("poster_path")
    val background: String? = null,
    @SerializedName("backdrop_path")
    val banner: String? = null,
    @SerializedName("genres")
    val generos: List<GeneroResponse>,
    @Expose
    val favorito: Boolean
) : Serializable
