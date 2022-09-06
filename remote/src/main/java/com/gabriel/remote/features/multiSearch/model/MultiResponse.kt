package com.gabriel.remote.features.multiSearch.model

import com.gabriel.remote.features.genero.model.GeneroResponse
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MultiResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("poster_path")
    val banner: String? = null,
    @SerializedName("overview")
    val description: String? = null,
    @SerializedName("vote_average")
    val nota: Double? = null,
    @SerializedName("genre_ids")
    val generos: List<GeneroResponse?>? = null,
    @SerializedName("media_type")
    val type: String? = null,
    @SerializedName("")
    val background: String? = null,
    @SerializedName("")
    val favorito: Boolean? = false
) : Serializable
