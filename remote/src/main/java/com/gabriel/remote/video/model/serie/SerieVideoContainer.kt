package com.gabriel.remote.video.model.serie

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SerieVideoContainer(
    @SerializedName("results")
    val results: List<SerieVideoResponse>
) : Serializable
