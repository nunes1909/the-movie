package com.gabriel.remote.traducao.model.serie

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TraducaoSerieResponse(
    @SerializedName("iso_3166_1")
    val pais: String,
    @SerializedName("iso_639_1")
    val linguagem: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("data")
    val filme: TraducaoSerie
) : Serializable
