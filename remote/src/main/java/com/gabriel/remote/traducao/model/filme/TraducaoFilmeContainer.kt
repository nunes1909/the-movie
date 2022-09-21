package com.gabriel.remote.traducao.model.filme

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TraducaoFilmeContainer(
    @SerializedName("translations")
    val translations: List<TraducaoFilmeResponse>
) : Serializable
