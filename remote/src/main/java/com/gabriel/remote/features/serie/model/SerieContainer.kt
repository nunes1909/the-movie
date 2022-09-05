package com.gabriel.remote.features.serie.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SerieContainer(
    @SerializedName("results")
    val results: List<SerieResponse>
) : Serializable
