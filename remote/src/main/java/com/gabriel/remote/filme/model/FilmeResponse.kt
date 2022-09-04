package com.gabriel.remote.filme.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FilmeResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("poster_path")
    val background: String? = null,
    @SerializedName("backdrop_path")
    val banner: String? = null
) : Serializable
