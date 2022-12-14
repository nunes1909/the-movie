package com.gabriel.remote.movie.modelsApi.serie

import com.gabriel.remote.genero.model.GeneroResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SerieDetailResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val title: String,
    @SerializedName("overview")
    val description: String? = null,
    @SerializedName("vote_average")
    val nota: Double? = null,
    @SerializedName("poster_path")
    val cartaz: String? = null,
    @SerializedName("backdrop_path")
    val banner: String? = null,
    @SerializedName("genres")
    val generos: List<GeneroResponse>? = null,
    @Expose
    val favorito: Boolean = false
) : Serializable
