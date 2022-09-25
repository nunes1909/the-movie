package com.gabriel.remote.movie.modelsApi.multi

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MultiResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("overview")
    val description: String? = null,
    @SerializedName("poster_path")
    val cartaz: String? = null,
    @SerializedName("backdrop_path")
    val banner: String? = null
) : Serializable
