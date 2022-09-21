package com.gabriel.remote.traducao.model.serie

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TraducaoSerieContainer(
    @SerializedName("translations")
    val translations: List<TraducaoSerieResponse>
) : Serializable
