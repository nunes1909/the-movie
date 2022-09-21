package com.gabriel.remote.traducao.model.filme

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TraducaoFilme(
    @SerializedName("title")
    val titulo: String,
    @SerializedName("overview")
    val descricao: String
) : Serializable
