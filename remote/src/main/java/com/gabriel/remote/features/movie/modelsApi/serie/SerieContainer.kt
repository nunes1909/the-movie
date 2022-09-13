package com.gabriel.remote.features.movie.modelsApi.serie

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SerieContainer(
    @SerializedName("results")
    val results: List<SerieResponse>
) : Serializable
