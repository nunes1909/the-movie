package com.gabriel.remote.features.movie.modelsApi.multi

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MultiContainer(
    @SerializedName("results")
    val results: List<MultiResponse>
) : Serializable
