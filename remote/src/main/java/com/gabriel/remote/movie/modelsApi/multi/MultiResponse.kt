package com.gabriel.remote.movie.modelsApi.multi

import com.gabriel.remote.genero.model.GeneroResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MultiResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val title: String,
    @SerializedName("overview")
    val description: String? = null,
    @SerializedName("media_type")
    val type: String? = null,
    @SerializedName("vote_average")
    val nota: Double? = null,
    @SerializedName("genre_ids")
    val generos: List<GeneroResponse>? = null,
    @Expose(serialize = false)
    val favorito: Boolean? = false,
    @SerializedName("poster_path")
    val cartaz: String? = null,
    @SerializedName("backdrop_path")
    val banner: String? = null
) : Serializable
