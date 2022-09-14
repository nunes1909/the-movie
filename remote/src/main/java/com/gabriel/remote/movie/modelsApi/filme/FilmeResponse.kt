package com.gabriel.remote.movie.modelsApi.filme

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FilmeResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("poster_path")
    val cartaz: String? = null,
    @SerializedName("backdrop_path")
    val banner: String? = null
) : Serializable
