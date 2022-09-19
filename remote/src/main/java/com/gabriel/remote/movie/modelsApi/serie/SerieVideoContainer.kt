package com.gabriel.remote.movie.modelsApi.serie

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SerieVideoContainer(
    @SerializedName("results")
    val results: List<SerieVideoResponse>
) : Serializable
