package com.gabriel.remote.movie.modelsApi.multi

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MultiContainer(
    @SerializedName("results")
    val results: List<MultiResponse>
) : Serializable
