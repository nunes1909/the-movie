package com.gabriel.remote.video.model.filme

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FilmeVideoResponse(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("key")
    val key: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("official")
    val official: Boolean? = null,
) : Serializable
