package com.gabriel.remote.traducao.model.filme

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TraducaoFilmeResponse(
    @SerializedName("iso_3166_1")
    val pais: String,
    @SerializedName("iso_639_1")
    val linguagem: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("data")
    val filme: TraducaoFilme
) : Serializable
