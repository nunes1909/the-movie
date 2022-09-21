package com.gabriel.remote.traducao.model.serie

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TraducaoSerie(
    @SerializedName("name")
    val titulo: String,
    @SerializedName("overview")
    val descricao: String
) : Serializable
