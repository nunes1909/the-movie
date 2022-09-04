package com.gabriel.remote.serie.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SerieSemelhanteContainer (
    @SerializedName("results")
    val results: List<SerieResponse>
) : Serializable
