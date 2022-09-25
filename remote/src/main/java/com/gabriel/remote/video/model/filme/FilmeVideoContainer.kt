package com.gabriel.remote.video.model.filme

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FilmeVideoContainer(
    @SerializedName("results")
    val results: List<FilmeVideoResponse>
) : Serializable
